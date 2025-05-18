package com.idus.worklog.dtos;

public class WorkShiftTypeDTO {
	private String name;
	private String description;

	public WorkShiftTypeDTO(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
