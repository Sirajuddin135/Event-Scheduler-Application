package com.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.exceptions.EventNotFoundException;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.EventDTO;
import com.calendar.services.EventService;

@RestController
@RequestMapping("/calender/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@PostMapping("")
	public ResponseEntity<EventDTO> postEvent(@RequestBody EventDTO eventDTO, @RequestParam String email) throws UserNotFoundException {
		EventDTO createdEvent = eventService.createEvent(eventDTO, email);
		
		return new ResponseEntity<EventDTO>(createdEvent, HttpStatus.CREATED);
	}
	
	@PutMapping("/{eventId}")
	public ResponseEntity<EventDTO> updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) throws EventNotFoundException {
		EventDTO updatedEvent = eventService.updateEvent(eventId, eventDTO);
		
		return new ResponseEntity<EventDTO>(updatedEvent, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{eventId}")
	public ResponseEntity<EventDTO> deleteEvent(@PathVariable Long eventId) throws EventNotFoundException {
		EventDTO deletedEvent = eventService.deleteEvent(eventId);
		
		return new ResponseEntity<EventDTO>(deletedEvent, HttpStatus.CREATED);
	}
}
