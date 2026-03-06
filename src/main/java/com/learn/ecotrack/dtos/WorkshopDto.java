package com.learn.ecotrack.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.learn.ecotrack.entities.Enrollment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WorkshopDto {
	
	
	private Integer id;
	private String name;
	private String description;
	private Integer price;
	private Integer duration;//days
	private String image;
	private LocalDateTime startDate;
	
	
	private List<Enrollment> enrollments;
	
	
	
	

}
