package com.idus.worklog.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.idus.worklog.dtos.UserDTO;
import com.idus.worklog.models.User;
import com.idus.worklog.services.UserService;


@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> users = userService.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findByID(@PathVariable Long id) {
		UserDTO obj = userService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody User obj) {
	    UserDTO userDTO = userService.insert(obj); 
	    URI uri = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(userDTO.getId()) 
	            .toUri();
	    return ResponseEntity.created(uri).body(userDTO);
	}

}
