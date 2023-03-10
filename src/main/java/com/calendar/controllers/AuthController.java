package com.calendar.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.entities.User;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.LoginCredentials;
import com.calendar.payloads.UserDTO;
import com.calendar.security.JWTUtil;
import com.calendar.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/calender")
@SecurityRequirement(name = "Event Scheduler")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public Map<String, Object> registerHandler(@Valid @RequestBody User user) throws UserNotFoundException {
		String encodedPass = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPass);
		
		UserDTO userDTO = userService.registerUser(user);
		
		String token = jwtUtil.generateToken(userDTO.getEmail());
		
		return Collections.singletonMap("jwt-token", token);
	}
	
	@PostMapping("/login")
	public Map<String, Object> loginHandler(@RequestBody LoginCredentials credentials) {
		try {
			UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
			
			authenticationManager.authenticate(authCredentials);
			
			String token = jwtUtil.generateToken(credentials.getEmail());
			
			return Collections.singletonMap("jwt-token", token);
			
		} catch (AuthenticationException authException) {
			throw new RuntimeException("Invalid login credentials !!");
		}
	}
}
