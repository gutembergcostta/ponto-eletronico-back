package com.idus.worklog.enums;

public enum Role {
	ADMIN(1),
	REGULAR(2);
	
	private int code;

	private Role(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Role valueOf(int code) {
		for (Role value : Role.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus Code");
		
	}
}
