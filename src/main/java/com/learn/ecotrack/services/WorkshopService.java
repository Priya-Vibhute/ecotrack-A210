package com.learn.ecotrack.services;

import java.util.List;

import com.learn.ecotrack.dtos.WorkshopDto;

public interface WorkshopService {
	
//	To add Workshop
	WorkshopDto addWorkshop(WorkshopDto workshopDto);
	
//  To update workshop 
	WorkshopDto updateWorkshop(Integer id,WorkshopDto workshopDto);
	
//  get workshop by id
	WorkshopDto getWorkshopById(Integer id);

//	delete workshop by id
	void deleteWorkshopById(Integer id);
	
//	get all workshops
	 List<WorkshopDto> getAllWorshops();
	

	
	

}
