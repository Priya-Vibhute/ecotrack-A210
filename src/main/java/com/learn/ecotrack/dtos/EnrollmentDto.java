package com.learn.ecotrack.dtos;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.entities.Workshop;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EnrollmentDto {
	
	@Id
	private Integer id;
	
	@ManyToOne
	private Workshop workshop;
	
	@ManyToOne
	private User user;
	
	private Integer amount;
	
	@CreationTimestamp
	private LocalDateTime enrollmentDate;
	
	
	
	

}
