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

	public WorkShift insert(WorkShift obj) {
		return repository.save(obj);
	}
}
