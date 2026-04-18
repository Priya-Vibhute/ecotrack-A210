package com.learn.ecotrack.services.impl;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.EnrollmentDto;
import com.learn.ecotrack.entities.Enrollment;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.enums.PaymentStatus;
import com.learn.ecotrack.repositories.EnrollmentRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.repositories.WorkshopRepository;
import com.learn.ecotrack.services.EnrollmentService;
import com.learn.ecotrack.services.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkshopRepository workshopRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RazorpayService razorpayService;

	@Override
	public EnrollmentDto enroll(String email, Integer workshopId) {
		
		if(enrollmentRepository.
				existsByUserEmailAndWorkshopId(email, workshopId))
			throw new RuntimeException("User has already enrolled");
		
		User user = userRepository.findByEmail(email)
		.orElseThrow(()->new RuntimeException("Email not found"));
		
		Workshop workshop = workshopRepository.findById(workshopId)
		.orElseThrow(()->new RuntimeException("Workshop not found"));
		
		Order order=null;
		try {
			order=razorpayService.createOrder((double)workshop.getPrice());
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Enrollment enrollment = new Enrollment();
		enrollment.setWorkshop(workshop);
		enrollment.setUser(user);
		enrollment.setAmount(workshop.getPrice());
		enrollment.setStatus(PaymentStatus.CREATED);
		enrollment.setOrderId(order.get("id"));
		
		Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
						
		return modelMapper.map(savedEnrollment, EnrollmentDto.class);
	}

	@Override
	public void confirmPayment(Map<String, String> payload) {
		
		String orderId = payload.get("razorpay_order_id");
		String paymentId=payload.get("razorpay_payment_id");
		String signature=payload.get("razorpay_signature");
		
		boolean verifyPaymentSignature = 
				razorpayService.verifyPaymentSignature(orderId, paymentId, signature);
		
		Enrollment enrollment = enrollmentRepository.findByOrderId(orderId)
		.orElseThrow(()->new RuntimeException("OrderId not found"));
		
		if(verifyPaymentSignature)
		{
			enrollment.setPaymentId(paymentId);
			enrollment.setStatus(PaymentStatus.SUCCESS);
		}
		else 
		{
			enrollment.setStatus(PaymentStatus.FAILED);
		}
		
		enrollmentRepository.save(enrollment);
		
		
	}

}
