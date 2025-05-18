package com.idus.worklog.dtos;

import com.idus.worklog.models.User;

public class UserDTO {
	private Long id;
	private String name;
	private String email;
	private String workShift;
	
	public UserDTO() {
		
	}

	public UserDTO(Long id, String name, String email, String workShift) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.workShift = workShift;
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.workShift = user.getWorkShift().getDescription();
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
		return workShift;
	}

	public void setWorkShift(String workShift) {
		this.workShift = workShift;
	}
	
	
}
