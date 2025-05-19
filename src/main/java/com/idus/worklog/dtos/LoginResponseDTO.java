package com.idus.worklog.dtos;

public record LoginResponseDTO(String token, Long userId, String role, String email) {

}
