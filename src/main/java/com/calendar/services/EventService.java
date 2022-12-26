package com.calendar.services;

import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.EventDTO;

public interface EventService {
	EventDTO createEvent(EventDTO eventDTO, String email) throws UserNotFoundException;
	
	EventDTO updateEvent(Long eventId, EventDTO eventDTO);
	
	EventDTO deleteEvent(Long eventId);
}
