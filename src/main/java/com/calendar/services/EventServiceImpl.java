package com.calendar.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.calendar.entities.Event;
import com.calendar.entities.User;
import com.calendar.exceptions.EventNotFoundException;
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
	public EventDTO createEvent(EventDTO eventDTO) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
//		User user = userRepo.findById(email)
//				.orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
		
		User user = userRepo.findById(email).get();

		Event event = modelMapper.map(eventDTO, Event.class);

		event.setUser(user);

		int startDay = event.getStartDate().getDayOfMonth();
		int endDay = event.getEndDate().getDayOfMonth();

		if (startDay - endDay == 0) {
			event.setEventType("Non-recurring");
		} else {
			event.setEventType("Recurring");
		}

		Event createdEvent = eventRepo.save(event);

		return modelMapper.map(createdEvent, EventDTO.class);
	}

	@Override
	public EventDTO updateEvent(Long eventId, EventDTO eventDTO) throws EventNotFoundException {
		Event event = eventRepo.findById(eventId)
				.orElseThrow(() -> new EventNotFoundException("Event not found with event id: " + eventId));
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(!email.equals(event.getUser().getEmail())) {
			throw new EventNotFoundException("You have not scheduled any event with event id " + eventId + " to update !!");
		}

		event.setStartDate(eventDTO.getStartDate());
		event.setEndDate(eventDTO.getEndDate());
		event.setStartTime(eventDTO.getStartTime());
		event.setEndTime(eventDTO.getEndTime());

		int startDay = event.getStartDate().getDayOfMonth();
		int endDay = event.getEndDate().getDayOfMonth();

		if (startDay - endDay == 0) {
			event.setEventType("Non-recurring");
		} else {
			event.setEventType("Recurring");
		}
		
		event = eventRepo.save(event);

		return modelMapper.map(event, EventDTO.class);
	}

	@Override
	public String deleteEvent(Long eventId) throws EventNotFoundException {
		Event event = eventRepo.findById(eventId)
				.orElseThrow(() -> new EventNotFoundException("Event not found with event id: " + eventId));
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(!email.equals(event.getUser().getEmail())) {
			throw new EventNotFoundException("You have not scheduled any event with id " + eventId + " to delete !!");
		}
		
		eventRepo.delete(event);
		
		return "Event with id " + eventId + " deleted successfully !!";
	}

}
