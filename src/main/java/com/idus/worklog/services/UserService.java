package com.idus.worklog.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idus.worklog.dtos.UserDTO;
import com.idus.worklog.models.User;
import com.idus.worklog.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public UserDTO findById(Long id) {
		User user = repository.findById(id).get();

		UserDTO userDTO = new UserDTO(user);
		return userDTO;
	}
	
	public UserDTO insert(User obj) {
		UserDTO userDTO = new UserDTO(repository.save(obj));
		
		return userDTO;
	}
}
