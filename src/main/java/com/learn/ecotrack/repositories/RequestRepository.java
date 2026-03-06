package com.learn.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecotrack.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

}
