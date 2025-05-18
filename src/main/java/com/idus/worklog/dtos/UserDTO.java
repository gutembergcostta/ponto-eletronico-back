package com.idus.worklog.dtos;

import com.idus.worklog.models.User;

public class UserDTO {
	private Long id;
	private String name;
	private String email;
	private String role;
	private String workShiftType;
	

	public UserDTO() {

	}

	public UserDTO(Long id, String name, String email, String workShiftType, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.workShiftType = workShiftType;
		this.role = role;
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.workShiftType = user.getWorkShift().getDescription();
		this.role = user.getRole().getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkShift() {
		return workShiftType;
	}

	public void setWorkShift(String workShift) {
		this.workShiftType = workShift;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
