package com.calendar.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

	@Id
	private String email;

	@Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must not contain numbers or special characters")
	@Size(min = 5, max = 20, message = "First Name must be between 5 and 30 characters long")
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name must not contain numbers or special characters")
	@Size(min = 5, max = 20, message = "Last Name must be between 5 and 30 characters long")
	private String lastName;

	@Pattern(regexp = "^\\d{10}$", message = "Mobile Number must contain only Numbers")
	@Size(min = 10, max = 10, message = "Mobile Number must be exactly 10 digits long")
	private String mobileNumber;

	private String password;
	private LocalDate dateOfBirth;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Event> events = new ArrayList<>();
}
