package com.calendar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calendar.entities.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
	
//	@Query("SELECT * FROM Event WHERE user_email =: 1 AND eventType =: 2")
//	List<Event> findByEmailAndEventType(String email, String eventType);
}
