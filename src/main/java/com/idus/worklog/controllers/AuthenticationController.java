package com.idus.worklog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idus.worklog.dtos.AuthenticationDTO;

import com.idus.worklog.dtos.RegisterDTO;

import com.idus.worklog.services.AuthenticationService;


@RestController
@RequestMapping("auth")
public class AuthenticationController {	
	
	@Autowired
	AuthenticationService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
		return authService.login(data);
	}
	
	@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data){
		return authService.registerNewUser(data);
    }
}
