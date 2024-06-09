package com.eduPortal.education_portal.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@NotBlank(message = "Admin name is mandatory")
	private  String adminName;

@NotBlank(message = "Contact is mandatory")
@Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Contact number is invalid")
	private String contact;

@NotBlank(message="Email id is mandatory")
@Email(message="Enter Valid email Id")
	private String emailId;

@NotBlank(message = "Password is mandatory")
@Size(min = 4, message = "Password should be at least 4 characters long")
@Size(max = 8, message = "Password must be at least 8 characters long")
	private  String password;



}
