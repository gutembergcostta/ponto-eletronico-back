package com.idus.worklog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idus.worklog.models.WorkShift;
import com.idus.worklog.repositories.WorkShiftRepository;

@Service
public class WorkShiftService {

	@Autowired
	private WorkShiftRepository repository;

	public List<WorkShift> findAll() {
		return repository.findAll();
	}
	
	public WorkShift findById(Long id) {
		WorkShift workShift = repository.findById(id).get();

		return workShift;
	}

	public WorkShift insert(WorkShift obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public WorkShift update(Long id, WorkShift obj) {
		WorkShift entity = repository.getReferenceById(id);
		
		updateData(entity, obj);
		
		return repository.save(entity);
	}

	private void updateData(WorkShift entity, WorkShift obj) {
		entity.setDescription(obj.getDescription());
		entity.setDailyHours(obj.getDailyHours());
		entity.setPauseMinutes(obj.getPauseMinutes());
	}
}
