package com.learn.ecotrack.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learn.ecotrack.dtos.WorkshopDto;
import com.learn.ecotrack.services.FileService;
import com.learn.ecotrack.services.WorkshopService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {
	
	@Autowired
	private WorkshopService workshopService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${workshop.images}")
	private String path;
	
	
	
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
	
	@PutMapping("/{id}/upload-image")
	public ResponseEntity<Map<String, String>> uploadImage(@PathVariable Integer id,
			@RequestParam("workshopImage") MultipartFile file)
	{
		
		String fileName = fileService.uploadFile(file, path);
		WorkshopDto workshop = workshopService.getWorkshopById(id);
		workshop.setImage(fileName);
		
		WorkshopDto updateWorkshop = workshopService.updateWorkshop(id, workshop);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("message", fileName+" uploaded successfully");	
		
		return ResponseEntity.ok(map);
	}
	
//	img src='localhost:8080/workshops/52/get-image'
	@GetMapping("/{id}/get-image")
	public void getImage(@PathVariable Integer id,HttpServletResponse response)
	{
		WorkshopDto workshop = workshopService.getWorkshopById(id);
		InputStream resource = fileService.getResource(path, workshop.getImage());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		try {
			StreamUtils.copy(resource, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	

}
