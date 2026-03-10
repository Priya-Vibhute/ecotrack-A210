package com.learn.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.learn.ecotrack.dtos.EnrollmentDto;
import com.learn.ecotrack.entities.Enrollment;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.repositories.EnrollmentRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.repositories.WorkshopRepository;
import com.learn.ecotrack.services.EnrollmentService;

public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkshopRepository workshopRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EnrollmentDto enroll(String email, Integer workshopId) {
		
		if(enrollmentRepository.
				existsByUserEmailAndWorkshopId(email, workshopId))
			throw new RuntimeException("User has already enrolled");
		
		User user = userRepository.findByEmail(email)
		.orElseThrow(()->new RuntimeException("Email not found"));
		
		Workshop workshop = workshopRepository.findById(workshopId)
		.orElseThrow(()->new RuntimeException("Workshop not found"));
		
		Enrollment enrollment = new Enrollment();
		enrollment.setWorkshop(workshop);
		enrollment.setUser(user);
		enrollment.setAmount(workshop.getPrice());
		
		Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
						
		return modelMapper.map(savedEnrollment, EnrollmentDto.class);
	}

}
