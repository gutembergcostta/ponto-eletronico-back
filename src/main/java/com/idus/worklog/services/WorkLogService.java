package com.idus.worklog.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.idus.worklog.dtos.WorkDaySummaryDTO;
import com.idus.worklog.enums.WorkLogStatus;
import com.idus.worklog.models.User;
import com.idus.worklog.models.WorkLog;
import com.idus.worklog.repositories.UserRepository;
import com.idus.worklog.repositories.WorkLogRepository;
import com.idus.worklog.services.exceptions.DatabaseException;
import com.idus.worklog.services.exceptions.ResourceNotFoundException;
import com.idus.worklog.strategy.WorkShift;
import com.idus.worklog.strategy.WorkShiftFactory;
import com.idus.worklog.utils.Utils;

@Service
public class WorkLogService {
	@Autowired
	private WorkLogRepository workLogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<WorkLog> findAll() {
		return workLogRepository.findAll();
	}
	
	public WorkDaySummaryDTO getDaySummary(Long userId) {
	    User user = userRepository.findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

	    WorkShift workShift = WorkShiftFactory.getStrategy(user.getWorkShiftType());

	    LocalDate today = LocalDate.now();
	    LocalDateTime startOfDay = today.atStartOfDay();
	    LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

	    List<WorkLog> workLogs = workLogRepository.findByUserIdAndWorkLogSelectedBetween(userId, startOfDay, endOfDay);

	    List<LocalDateTime> selectedDates = workLogs.stream()
	        .map(WorkLog::getWorkLogSelected)
	        .sorted()
	        .collect(Collectors.toList());

	    Duration workedDuration = workShift.sumWorkIntervals(selectedDates);
	    Duration expectedDuration = workShift.expectedDuration();
	    Duration exceededDuration = workedDuration.minus(expectedDuration).isNegative() ? Duration.ZERO : workedDuration.minus(expectedDuration);
	    Duration remainingDuration = expectedDuration.minus(workedDuration).isNegative() ? Duration.ZERO : expectedDuration.minus(workedDuration);
	    Boolean isComplete = expectedDuration.minus(workedDuration).isNegative() ? true : false;
	    
	    return new WorkDaySummaryDTO(
	        today,
	        selectedDates,
	        workedDuration,
	        expectedDuration,
	        remainingDuration,
	        exceededDuration,
	        isComplete
	    );
	}

	public WorkLog registerWorkLog(WorkLog obj) {
		Optional<WorkLog> openLog = workLogRepository.findByUserIdAndWorkLogStatus(obj.getUser().getId(), WorkLogStatus.OPEN);
		if (openLog.isPresent()) {
			WorkLog previousLog = openLog.get();
			previousLog.setWorkLogStatus(WorkLogStatus.CLOSED);
			workLogRepository.save(previousLog);
			
			
			LocalDateTime actualDateTime      = LocalDateTime.now();
			obj.setDelayedCheckIn(Utils.isDelayed(obj.getWorkLogSelected(), actualDateTime));
			obj.setWorkLogStatus(WorkLogStatus.CLOSED);
		} else {
			obj.setWorkLogStatus(WorkLogStatus.OPEN);
		}
		
		return workLogRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			workLogRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

}
