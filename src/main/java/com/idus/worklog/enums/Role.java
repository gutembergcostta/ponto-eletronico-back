package com.idus.worklog.enums;

public enum Role {
	ADMIN("Administrador"), REGULAR("Usuário Comum");

	private final String description;

	private Role(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	
}
