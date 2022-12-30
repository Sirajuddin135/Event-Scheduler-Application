package com.calendar.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.entities.User;
import com.calendar.exceptions.EventNotFoundException;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.EventDTO;
import com.calendar.payloads.UserDTO;
import com.calendar.repositories.UserRepo;
import com.calendar.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/calender")
@SecurityRequirement(name = "Event Scheduler")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@PutMapping("/user")
	public ResponseEntity<UserDTO> updateUser(@RequestBody User user) throws UserNotFoundException {
		UserDTO updatedUserDTO = userService.updateUser(user);

		return new ResponseEntity<UserDTO>(updatedUserDTO, HttpStatus.CREATED);
	}

	@GetMapping("/user/info")
	public ResponseEntity<UserDTO> getUserDetails() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = userRepo.findById(email).get();

		return new ResponseEntity<UserDTO>(modelMapper.map(user, UserDTO.class), HttpStatus.OK);
	}

	@GetMapping("/event/{type}")
	public ResponseEntity<List<EventDTO>> getAllEventsByType(@PathVariable String type) throws EventNotFoundException {
		List<EventDTO> events = userService.getEventsByType(type);

		return new ResponseEntity<List<EventDTO>>(events, HttpStatus.OK);
	}
}
