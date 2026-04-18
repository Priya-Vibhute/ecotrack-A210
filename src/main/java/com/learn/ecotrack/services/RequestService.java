package com.learn.ecotrack.services;

import java.util.List;

import com.learn.ecotrack.dtos.RequestDto;

public interface RequestService {
	
	 RequestDto raiseRequest(String email,RequestDto requestDto);
	 
	 RequestDto approveRequest(Integer requestId);
	 
	 RequestDto rejectRequest(Integer requestId);
	 
	 List<RequestDto> getRequestByEmail(String email);

}
