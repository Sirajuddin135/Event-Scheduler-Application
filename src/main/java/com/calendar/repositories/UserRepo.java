package com.calendar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

}
