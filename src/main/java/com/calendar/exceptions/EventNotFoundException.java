package com.calendar.exceptions;

public class EventNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public EventNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public EventNotFoundException(String message) {
		super(message);
	}
}
