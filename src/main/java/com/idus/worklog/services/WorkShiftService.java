package com.idus.worklog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.idus.worklog.models.WorkShift;
import com.idus.worklog.repositories.WorkShiftRepository;
import com.idus.worklog.services.exceptions.DatabaseException;
import com.idus.worklog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkShiftService {

	@Autowired
	private WorkShiftRepository repository;

	public List<WorkShift> findAll() {
		return repository.findAll();
	}

	public WorkShift findById(Long id) {
		Optional<WorkShift> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public WorkShift insert(WorkShift obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public WorkShift update(Long id, WorkShift obj) {
		try {
			WorkShift entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);

		}
	}

	private void updateData(WorkShift entity, WorkShift obj) {
		entity.setDescription(obj.getDescription());
		entity.setDailyHours(obj.getDailyHours());
		entity.setPauseMinutes(obj.getPauseMinutes());
	}
}
