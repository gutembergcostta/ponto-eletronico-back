package com.idus.worklog.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idus.worklog.enums.WorkLogStatus;
import com.idus.worklog.models.WorkLog;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
	Optional<WorkLog> findByUserIdAndWorkLogStatus(Long userId, WorkLogStatus status);
	
	List<WorkLog> findByUserIdAndWorkLogSelectedBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
