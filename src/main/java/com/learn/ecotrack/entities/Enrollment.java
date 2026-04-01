package com.learn.ecotrack.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.learn.ecotrack.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Workshop workshop;
	
	@ManyToOne
	private User user;
	
	private Integer amount;
	
	@CreationTimestamp
	private LocalDateTime enrollmentDate;
	
	private String paymentId;
	private String orderId;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;	

}
