package com.learn.ecotrack.services;

public interface EmailService {
	
	void sendMail(String to ,String subject ,String body);
	void sendMail(String to[] ,String subject ,String body);

}
