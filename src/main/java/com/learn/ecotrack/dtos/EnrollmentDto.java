package com.learn.ecotrack.dtos;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EnrollmentDto {
	

	private Integer id;
	

	private Workshop workshop;
	
	
	private User user;
	
	private Integer amount;
	

	private LocalDateTime enrollmentDate;
	
	private String paymentId;
	private String orderId;
	private PaymentStatus status;		
	
}
