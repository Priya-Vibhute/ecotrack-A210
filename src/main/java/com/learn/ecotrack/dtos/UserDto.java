package com.learn.ecotrack.dtos;

import java.util.List;

import com.learn.ecotrack.entities.Enrollment;
import com.learn.ecotrack.entities.Request;
import com.learn.ecotrack.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String id;

	private String firstName;
	
	private String lastName;

	private String email;
	
	private String password;
	
	private Role role;
	
	private List<Request> requests;
	
	private List<Enrollment> enrollments;
	

}
