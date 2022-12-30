package com.calendar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calendar.entities.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
	
	@Query(value = "SELECT e FROM Event e WHERE user.email = ?1 AND CASE WHEN ?2 = 'day' THEN startDate = CURDATE() "
			+ "WHEN ?2 = 'month' THEN (MONTH(startDate) = MONTH(CURDATE()) AND YEAR(startDate) = YEAR(CURDATE())) "
			+ "ELSE WEEK(startDate) = WEEK(CURDATE()) END")
	List<Event> findByEventType(String email, String type);
}
