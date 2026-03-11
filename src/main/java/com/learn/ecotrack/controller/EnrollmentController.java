package com.learn.ecotrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.EnrollmentDto;
import com.learn.ecotrack.services.EnrollmentService;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@PostMapping("/{email}/{workshopId}")
	public ResponseEntity<EnrollmentDto> enroll(@PathVariable String email,
			                                   @PathVariable int workshopId)
	{
		return new ResponseEntity<EnrollmentDto>
		             (enrollmentService.enroll(email, workshopId),
		            		                         HttpStatus.CREATED);
	}

}
