package com.learn.ecotrack.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendMail(String to, String subject, String body) {
	
		 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		 simpleMailMessage.setTo(to);
		 simpleMailMessage.setSubject(subject);
		 simpleMailMessage.setText(body);
		 
		 javaMailSender.send(simpleMailMessage);
		 
	}

	@Override
	public void sendMail(String[] to, String subject, String body) {
		 
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		 simpleMailMessage.setTo(to);
		 simpleMailMessage.setSubject(subject);
		 simpleMailMessage.setText(body);
		 
		 javaMailSender.send(simpleMailMessage);
		 
		
	}

}
