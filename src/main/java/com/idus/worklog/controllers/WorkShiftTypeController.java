package com.idus.worklog.controllers;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idus.worklog.dtos.WorkShiftTypeDTO;
import com.idus.worklog.enums.WorkShiftType;
import java.util.List;

@RestController
@RequestMapping(value = "/work-shift-types")
public class WorkShiftTypeController {

	@GetMapping
	public List<WorkShiftTypeDTO> getWorkShiftTypes() {
		return Arrays
				.stream(WorkShiftType.values())
				.map(ws -> new WorkShiftTypeDTO(ws.name(), ws.getDescription()))
				.collect(Collectors.toList());
	}
}
