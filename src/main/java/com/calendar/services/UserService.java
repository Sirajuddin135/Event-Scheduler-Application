package com.calendar.services;

import java.util.List;

import com.calendar.entities.Event;
import com.calendar.entities.User;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.UserDTO;

public interface UserService {

	UserDTO registerUser(User user) throws UserNotFoundException;

	UserDTO updateUser(User user) throws UserNotFoundException;

	List<Event> getEventsByType(String email, String eventType);
}
