package com.calendar.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgNotValidException(MethodArgumentNotValidException e, WebRequest req) {
		Map<String, String> res = new HashMap<>();
		
		e.getBindingResult().getAllErrors().forEach(err -> {
			String fieldName = ((FieldError) err).getField();
			String message = err.getDefaultMessage();
			
			res.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
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
