package com.calendar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.entities.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

}
