package com.learn.ecotrack.security;

import com.learn.ecotrack.dtos.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	
	private String token;
	private UserDto userDto; 

}
