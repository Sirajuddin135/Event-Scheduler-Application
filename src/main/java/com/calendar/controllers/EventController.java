package com.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.EventDTO;
import com.calendar.services.EventService;

@RestController
@RequestMapping("/masaicalender/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@PostMapping("")
	public ResponseEntity<EventDTO> postEvent(@RequestBody EventDTO eventDTO, @RequestParam String email) throws UserNotFoundException {
		EventDTO createdEvent = eventService.createEvent(eventDTO, email);
		
		return new ResponseEntity<EventDTO>(createdEvent, HttpStatus.CREATED);
	}
}
