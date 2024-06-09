package com.eduPortal.education_portal.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
	public class Course {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int courseId;
	    
	    
	    private int adminId;
	    
	    @NotBlank(message = "Title is mandatory")
	    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	    private String title;

	    @NotBlank(message = "Description is mandatory")
	    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
	    private String description;


@NotNull(message = "Price is mandatory")
@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
private double price;


	    
	  
	    
	}


