package com.idus.worklog.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.idus.worklog.dtos.UserDTO;
import com.idus.worklog.models.WorkShift;
import com.idus.worklog.services.WorkShiftService;

@RestController
@RequestMapping(value = "/workshifts")
public class WorkShiftController {

	@Autowired
	private WorkShiftService workShiftService;

	@GetMapping
	public ResponseEntity<List<WorkShift>> findAll() {
		List<WorkShift> users = workShiftService.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping(value = "/{id}")
	public WorkShift findByID(@PathVariable Long id) {
		return workShiftService.findById(id);
	}

	@PostMapping
	public ResponseEntity<WorkShift> insert(@RequestBody WorkShift obj) {
		obj = workShiftService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		workShiftService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<WorkShift> update(@PathVariable Long id, @RequestBody WorkShift obj){
		obj = workShiftService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
