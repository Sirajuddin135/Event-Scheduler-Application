package com.calendar.services;

import com.calendar.entities.User;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.UserDTO;

public interface UserService {

	UserDTO registerUser(User user) throws UserNotFoundException;

	UserDTO loginUser(String email, String mobileNumber) throws UserNotFoundException;

	UserDTO updateUser(User user) throws UserNotFoundException;

//	List<Event> getEventsByType(String eventType);
}
