package com.learn.ecotrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.RequestDto;
import com.learn.ecotrack.services.RequestService;

@RestController
@RequestMapping("/requests")
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	
	@PostMapping("/{email}")
	public ResponseEntity<RequestDto> raiseRequest
	                             (@PathVariable String email,
	                            		 @RequestBody RequestDto requestDto)
	{
		return new ResponseEntity<RequestDto>(requestService.raiseRequest(email, requestDto),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/{requestId}/approve")
	public ResponseEntity<RequestDto> approveRequest(@PathVariable Integer requestId)
	{
		return ResponseEntity.ok(requestService.approveRequest(requestId));
	}
	
	
	@PutMapping("/{requestId}/reject")
	public ResponseEntity<RequestDto> rejectRequest(@PathVariable Integer requestId)
	{
		return ResponseEntity.ok(requestService.rejectRequest(requestId));
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<List<RequestDto>> getRequestsByEmail
	(@PathVariable String email)
	{
		return ResponseEntity.ok(requestService.getRequestByEmail(email));
	}
	
	

}
