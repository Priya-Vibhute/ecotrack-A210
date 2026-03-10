package com.learn.ecotrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.UserDto;
import com.learn.ecotrack.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody UserDto userDto)
	{
		UserDto savedUser = userService.registerUser(userDto);
		return new ResponseEntity<UserDto>(savedUser,HttpStatus.CREATED);
	}
	

	@GetMapping("/check-email")
	public ResponseEntity<Boolean> checkEmail(@RequestParam String email)
	{
		return ResponseEntity.ok(userService.checkEmailExists(email));
	}
	
	

}
