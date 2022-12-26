package com.calendar.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private String email;
	
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private LocalDate dateOfBirth;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Event> events = new HashSet<>();
}
