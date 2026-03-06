package com.learn.ecotrack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.WorkshopDto;
import com.learn.ecotrack.services.WorkshopService;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {
	
	@Autowired
	private WorkshopService workshopService;
	
	@PostMapping
	public ResponseEntity<WorkshopDto> addWorkshop
	                        (@RequestBody WorkshopDto workshopDto)
	{
		WorkshopDto savedWorkshop 
		          = workshopService.addWorkshop(workshopDto);
		return new ResponseEntity<WorkshopDto>(savedWorkshop, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<WorkshopDto>> getAllWorkshops()
	{
		return new ResponseEntity<List<WorkshopDto>>
		(workshopService.getAllWorshops(),HttpStatus.OK);
		
//	Method 2:	return ResponseEntity.ok(workshopService.getAllWorshops());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WorkshopDto> getWorkshopById(@PathVariable Integer id)
	{
		return ResponseEntity.ok(workshopService.getWorkshopById(id));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteWorkshop(@PathVariable Integer id)
	{
		workshopService.deleteWorkshopById(id);
		Map<String, String> response=new HashMap<String, String>();
		response.put("message", "Workshop deleted");
      	return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<WorkshopDto> updateWorkshop(@PathVariable Integer id,
			@RequestBody WorkshopDto workshopDto)
	{
		return ResponseEntity.ok(workshopService.updateWorkshop(id, workshopDto));
	}
	
	
	

}
