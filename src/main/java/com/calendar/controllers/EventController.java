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
import org.springframework.web.bind.annotation.RestController;

import com.calendar.exceptions.EventNotFoundException;
import com.calendar.payloads.EventDTO;
import com.calendar.services.EventService;

@RestController
@RequestMapping("/calender/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@PostMapping("")
	public ResponseEntity<EventDTO> postEvent(@RequestBody EventDTO eventDTO) {
		EventDTO createdEvent = eventService.createEvent(eventDTO);
		
		return new ResponseEntity<EventDTO>(createdEvent, HttpStatus.CREATED);
	}
	
	@PutMapping("/{eventId}")
	public ResponseEntity<EventDTO> updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) throws EventNotFoundException {
		EventDTO updatedEvent = eventService.updateEvent(eventId, eventDTO);
		
		return new ResponseEntity<EventDTO>(updatedEvent, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{eventId}")
	public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) throws EventNotFoundException {
		String response = eventService.deleteEvent(eventId);
		
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
}
