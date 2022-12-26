package com.calendar.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.entities.User;
import com.calendar.exceptions.UserNotFoundException;
import com.calendar.payloads.UserDTO;
import com.calendar.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO registerUser(User user) throws UserNotFoundException {
		Optional<User> opt = userRepo.findById(user.getEmail());

		if (opt.isPresent()) {
			throw new UserNotFoundException("User already exists with the email id: " + user.getEmail());
		}

		User registeredUser = userRepo.save(user);

		UserDTO registeredUserDTO = modelMapper.map(registeredUser, UserDTO.class);

		return registeredUserDTO;
	}

	@Override
	public UserDTO loginUser(String email, String mobileNumber) throws UserNotFoundException {
		User user = userRepo.findById(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

		if (!user.getMobileNumber().equals(mobileNumber)) {
			throw new UserNotFoundException("User mobile number doesn't match with: " + mobileNumber);
		}

		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(User user) throws UserNotFoundException {
		userRepo.findById(user.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found with email: " + user.getEmail()));

		User updatedUser = userRepo.save(user);

		UserDTO userDTO = modelMapper.map(updatedUser, UserDTO.class);

		return userDTO;
	}

}
