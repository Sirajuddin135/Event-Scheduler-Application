package com.calendar.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorMessage> methodArgNotValidException(MethodArgumentNotValidException e, WebRequest req) {
		MyErrorMessage err = new MyErrorMessage(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorMessage>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorMessage> exception(Exception e, WebRequest req) {
		MyErrorMessage err = new MyErrorMessage(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorMessage>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MyErrorMessage> userNotFoundException(UserNotFoundException e, WebRequest req) {
		MyErrorMessage err = new MyErrorMessage(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorMessage>(err, HttpStatus.BAD_REQUEST);
	}
}
