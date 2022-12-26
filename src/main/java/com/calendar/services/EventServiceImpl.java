package com.calendar.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.entities.Event;
import com.calendar.entities.User;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.EventDTO;
import com.calendar.repositories.EventRepo;
import com.calendar.repositories.UserRepo;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepo eventRepo;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EventDTO createEvent(EventDTO eventDTO, String email) throws UserNotFoundException {
		User user = userRepo.findById(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
		
		Event event = modelMapper.map(eventDTO, Event.class);
		
		event.setUser(user);
		
		int startDay = event.getStartDate().getDayOfMonth();
		int endDay = event.getEndDate().getDayOfMonth();
		
		if(startDay - endDay == 0) {
			event.setEventType("Non-recurring");
		} else {
			event.setEventType("Recurring");
		}
		
		Event createdEvent = eventRepo.save(event);
		
		return modelMapper.map(createdEvent, EventDTO.class);
	}

	@Override
	public EventDTO updateEvent(Long eventId, EventDTO eventDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventDTO deleteEvent(Long eventId) {
		// TODO Auto-generated method stub
		return null;
	}

}
