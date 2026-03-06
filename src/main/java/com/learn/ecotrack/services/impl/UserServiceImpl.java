package com.learn.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.learn.ecotrack.dtos.UserDto;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.services.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto registerUser(UserDto userDto) {
		
		return null;
	}

}
