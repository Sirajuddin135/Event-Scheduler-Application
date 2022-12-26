package com.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.entities.User;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.UserDTO;
import com.calendar.services.UserService;

@RestController
@RequestMapping("/masaicalender")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user) throws UserNotFoundException {
		UserDTO registeredUserDTO = userService.registerUser(user);
		
		return new ResponseEntity<UserDTO>(registeredUserDTO, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> loginUser(@RequestParam String email, @RequestParam String mobileNumber) throws UserNotFoundException {
		UserDTO loggedInsUserDTO = userService.loginUser(email, mobileNumber);
		
		return new ResponseEntity<UserDTO>(loggedInsUserDTO, HttpStatus.CREATED);
	}

	@PutMapping("/user")
	public ResponseEntity<UserDTO> updateUser(@RequestBody User user) throws UserNotFoundException {
		UserDTO updatedUserDTO = userService.updateUser(user);
		
		return new ResponseEntity<UserDTO>(updatedUserDTO, HttpStatus.CREATED);
	}
}
