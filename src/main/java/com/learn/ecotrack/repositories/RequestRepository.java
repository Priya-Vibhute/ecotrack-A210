package com.learn.ecotrack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecotrack.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

	
List<Request> findByUserEmail(String email);
}
