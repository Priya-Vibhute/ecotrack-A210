package com.learn.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.RequestDto;
import com.learn.ecotrack.entities.Request;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.enums.Status;
import com.learn.ecotrack.repositories.RequestRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.services.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RequestRepository requestRepository;

	@Override
	public RequestDto raiseRequest(String email,RequestDto requestDto) {
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(()->new RuntimeException("Email not found"));
		
		Request request = modelMapper.map(requestDto,Request.class);
		request.setUser(user);
		request.setStatus(Status.PENDING);
		Request savedRequest = requestRepository.save(request);
		
		return modelMapper.map(savedRequest, RequestDto.class);
	}

	@Override
	public RequestDto approveRequest(Integer requestId) {
		
		Request request = requestRepository.findById(requestId)
		.orElseThrow(()->new RuntimeException("request not found"));
		
		request.setStatus(Status.APPROVED);
	
		Request savedRequest = requestRepository.save(request);
		
		return modelMapper.map(savedRequest, RequestDto.class);
	}

	@Override
	public RequestDto rejectRequest(Integer requestId) {
		
		Request request = requestRepository.findById(requestId)
				.orElseThrow(()->new RuntimeException("request not found"));
		
		request.setStatus(Status.REJECTED);
		
		Request savedRequest = requestRepository.save(request);
		
		return modelMapper.map(savedRequest, RequestDto.class);
	}

}
