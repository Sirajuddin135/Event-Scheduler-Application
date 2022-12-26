package com.calendar.payloads;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String email;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private LocalDate dateOfBirth;
	
//	private Set<EventDTO> events = new HashSet<>();
}
