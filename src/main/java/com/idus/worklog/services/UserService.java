package com.idus.worklog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.idus.worklog.dtos.UserDTO;
import com.idus.worklog.models.User;
import com.idus.worklog.repositories.UserRepository;
import com.idus.worklog.services.exceptions.DatabaseException;
import com.idus.worklog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public List<UserDTO> findAll() {
		List<User> users = repository.findAll();
		return users.stream().map(UserDTO::new).collect(Collectors.toList());
	}

	public UserDTO findById(Long id) {
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(user);
	}

	public UserDTO createUser(User obj) {
		UserDTO userDTO = new UserDTO(repository.save(obj));

		return userDTO;
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setRole(obj.getRole());
		entity.setWorkShiftType(obj.getWorkShiftType());
	}

	public UserDTO updateUser(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			UserDTO userDTO = new UserDTO(repository.save(entity));

			return userDTO;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);

		}
	}

	public void deleteUser(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
