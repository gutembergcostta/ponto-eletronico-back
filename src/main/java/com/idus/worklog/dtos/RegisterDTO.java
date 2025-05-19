package com.idus.worklog.dtos;

import com.idus.worklog.enums.Role;
import com.idus.worklog.enums.WorkShiftType;

public record RegisterDTO(String name, String email, String password, Role role, WorkShiftType workShiftType) {

}
