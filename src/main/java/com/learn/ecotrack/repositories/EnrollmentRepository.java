package com.learn.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecotrack.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
	
	boolean existsByUserEmailAndWorkshopId
	(String email,Integer workshopId);
	

}
