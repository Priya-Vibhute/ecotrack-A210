package com.learn.ecotrack.services;

import java.util.Map;

import com.learn.ecotrack.dtos.EnrollmentDto;

public interface EnrollmentService {
	
	EnrollmentDto enroll(String email,Integer workshopId);
	 boolean confirmPayment(Map<String, String> payload);

}
