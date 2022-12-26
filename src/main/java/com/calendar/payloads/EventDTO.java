package com.calendar.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class EventDTO {
	
	private Long eventId;
	private String eventType;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private UserDTO user;
}
