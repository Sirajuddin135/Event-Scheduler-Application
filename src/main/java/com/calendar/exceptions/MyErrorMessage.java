package com.calendar.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorMessage {
	
	private LocalDateTime timeStamp;
	private String message;
	private String description;
	
}
