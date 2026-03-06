package com.learn.ecotrack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecotrack.entities.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer>{

	List<Workshop> findByName(String name);
	List<Workshop> findByPriceLessThanEqual
	                             (Integer price);
	
	List<Workshop> findByPriceBetween
    (Integer sp,Integer ep);
	
}
