package com.idus.worklog.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idus.worklog.dtos.RoleDTO;
import com.idus.worklog.enums.Role;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {
	@GetMapping
	public List<RoleDTO> getWorkShiftTypes() {
		return Arrays.stream(Role.values()).map(ws -> new RoleDTO(ws.name(), ws.getDescription()))
				.collect(Collectors.toList());
	}
}
