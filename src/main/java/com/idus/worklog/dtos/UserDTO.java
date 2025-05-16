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
		this.workShift = user.getWorkShift();
	}
}
