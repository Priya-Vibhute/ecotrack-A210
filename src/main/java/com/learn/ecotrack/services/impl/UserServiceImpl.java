package com.learn.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.UserDto;
import com.learn.ecotrack.entities.Role;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.enums.AppRole;
import com.learn.ecotrack.repositories.RoleRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.services.EmailService;
import com.learn.ecotrack.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	

	@Override
	@Transactional
	public UserDto registerUser(UserDto userDto) {
		
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		User user = modelMapper.map(userDto, User.class);
		
		Role role = roleRepository.findByRoleName(AppRole.ROLE_USER)
		.orElseThrow(()->new RuntimeException("role not found"));
		
		user.setRole(role);
		
		User savedUser = userRepository.save(user);
		
		System.out.println(savedUser.getEmail());
		emailService.sendMail(savedUser.getEmail(),
				"Registration Completed", "User registration completed.....");
		
		return modelMapper.map(savedUser, UserDto.class);
	}
	
	public boolean checkEmailExists(String email)
	{
		return userRepository.existsByEmail(email);
	}
	
	

	
	

}
