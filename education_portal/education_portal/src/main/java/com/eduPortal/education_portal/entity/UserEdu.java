package com.eduPortal.education_portal.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEdu{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;


@NotBlank(message = "user name is mandatory")
private String userName;

@NotBlank(message = "Password is mandatory")
@Size(min = 4, message = "Password should be at least 4 characters long")
@Size(max = 8, message = "Password must be at least 8 characters long")
private  String password;

@NotBlank(message="Email id is mandatory")
@Email(message="Enter Valid email Id")
private String email;

@NotBlank(message="location id is mandatory")
private String location;

@JsonIgnore
@OneToMany(mappedBy = "user")
private List<Cart> cart;


}
