package com.idus.worklog.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.idus.worklog.dtos.WorkDaySummaryDTO;
import com.idus.worklog.models.WorkLog;
import com.idus.worklog.services.WorkLogService;


@RestController
@RequestMapping(value = "/worklogs")
public class WorkLogController {
	
	@Autowired
	private WorkLogService workLogService;
	
	@GetMapping
	public ResponseEntity<List<WorkLog>> findAll(){
		List<WorkLog> list = workLogService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<WorkLog> registerWorkLog(@RequestBody WorkLog obj){
		obj = workLogService.registerWorkLog(obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@GetMapping("/today/{id}")
	public ResponseEntity<WorkDaySummaryDTO> getDaySummary(@PathVariable Long id){
		WorkDaySummaryDTO obj = workLogService.getDaySummary(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
