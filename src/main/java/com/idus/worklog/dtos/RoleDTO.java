package com.idus.worklog.dtos;

public class RoleDTO {
	private String name;
	private String description;

	public RoleDTO(String name, String description) {
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