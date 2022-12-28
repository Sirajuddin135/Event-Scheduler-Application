package com.calendar.services;

import com.calendar.exceptions.EventNotFoundException;
import com.calendar.payloads.EventDTO;

public interface EventService {
	EventDTO createEvent(EventDTO eventDTO);
	
	EventDTO updateEvent(Long eventId, EventDTO eventDTO) throws EventNotFoundException;
	
	String deleteEvent(Long eventId) throws EventNotFoundException;
}
