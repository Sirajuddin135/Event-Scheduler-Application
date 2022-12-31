package com.calendar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.calendar.entities.Event;
import com.calendar.entities.User;
import com.calendar.exceptions.EventNotFoundException;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.EventDTO;
import com.calendar.payloads.UserDTO;
import com.calendar.repositories.EventRepo;
import com.calendar.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EventRepo eventRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO registerUser(User user) throws UserNotFoundException {
		Optional<User> opt = userRepo.findById(user.getEmail());

		if (opt.isPresent()) {
			throw new UserNotFoundException("User already exists with the email id: " + user.getEmail());
		}

		User registeredUser = userRepo.save(user);

		UserDTO registeredUserDTO = modelMapper.map(registeredUser, UserDTO.class);

		return registeredUserDTO;
	}

	@Override
	public UserDTO updateUser(User user) throws UserNotFoundException {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(!email.equals(user.getEmail())) {
			throw new UserNotFoundException("You have not registered with email " + user.getEmail() + " to update details !!");
		}
		
		String encodedPass = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPass);

		User updatedUser = userRepo.save(user);

		UserDTO userDTO = modelMapper.map(updatedUser, UserDTO.class);

		return userDTO;
	}

	@Override
	public List<EventDTO> getEventsByType(String eventType) throws EventNotFoundException {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Event> events = eventRepo.findByEventType(email, eventType);
		
		if(events.size() == 0) {
			throw new EventNotFoundException("No events are scheduled !!");
		}
		
		List<EventDTO> eventDTOs = events.stream().map(event -> modelMapper.map(event, EventDTO.class)).collect(Collectors.toList());
		
		return eventDTOs;
	}

}
