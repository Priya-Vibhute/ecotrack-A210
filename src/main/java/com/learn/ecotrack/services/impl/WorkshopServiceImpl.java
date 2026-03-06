package com.learn.ecotrack.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.WorkshopDto;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.repositories.WorkshopRepository;
import com.learn.ecotrack.services.WorkshopService;

@Service
public class WorkshopServiceImpl implements WorkshopService {
	
	@Autowired
	private WorkshopRepository workshopRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public WorkshopDto addWorkshop(WorkshopDto workshopDto) {	
		
		Workshop workshop = modelMapper.map(workshopDto, Workshop.class);
		Workshop savedWorkshop = workshopRepository.save(workshop);
		return modelMapper.map(savedWorkshop, WorkshopDto.class);
	}

	@Override
	public WorkshopDto updateWorkshop(Integer id, WorkshopDto workshopDto) {
		
		Workshop workshop=workshopRepository.findById(id)
		.orElseThrow(()->new RuntimeException("Workshop not found"));
		
		workshop.setName(workshopDto.getName());
		workshop.setDescription(workshopDto.getDescription());
		workshop.setDuration(workshopDto.getDuration());
		workshop.setPrice(workshopDto.getPrice());
		workshop.setStartDate(workshopDto.getStartDate());
		workshop.setImage(workshopDto.getImage());	
		
		Workshop savedWorkshop = workshopRepository.save(workshop);
	    	
		return modelMapper.map(savedWorkshop, WorkshopDto.class);
	}

	@Override
	public WorkshopDto getWorkshopById(Integer id) {
		
		Workshop workshop = workshopRepository.findById(id)
		.orElseThrow(()->new RuntimeException("Id not found"));	
		return modelMapper.map(workshop, WorkshopDto.class);
	}

	@Override
	public void deleteWorkshopById(Integer id) {
		
		Workshop workshop = workshopRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not found"));	
		workshopRepository.delete(workshop);
		
		
	}

	@Override
	public List<WorkshopDto> getAllWorshops() {
		List<Workshop> workshops = workshopRepository.findAll();
		return workshops.stream()
				.map(w->modelMapper.map(w, WorkshopDto.class))
				.toList();	
	}
	
	
	
	

}
