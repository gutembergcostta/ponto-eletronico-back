package com.idus.worklog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.idus.worklog.dtos.AuthenticationDTO;
import com.idus.worklog.dtos.LoginResponseDTO;
import com.idus.worklog.dtos.RegisterDTO;
import com.idus.worklog.models.User;
import com.idus.worklog.repositories.UserRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public ResponseEntity<?> login(AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		User user = (User) auth.getPrincipal();
		return ResponseEntity.ok(new LoginResponseDTO(token, user.getId(), user.getRole().name(), user.getEmail()));
	}
	

    public ResponseEntity<?> registerNewUser(RegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) != null){
            return ResponseEntity
                    .badRequest()
                    .body("Email já cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role(), data.workShiftType());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
