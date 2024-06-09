package com.eduPortal.education_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduPortal.education_portal.dto.UserDto;
import com.eduPortal.education_portal.entity.PurchasedCourse;
import com.eduPortal.education_portal.entity.UserEdu;
import com.eduPortal.education_portal.service.IUserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService iUserService;
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody @Valid UserEdu user)
	{
		UserEdu userEdu =iUserService.createUser(user);
	     ResponseEntity<?> responseEntity = new ResponseEntity<>(userEdu, HttpStatus.CREATED);
			return responseEntity;	
	}
	
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<?> UserLogin(@PathVariable String email, @PathVariable String password)
	{
		UserDto userDto =iUserService.userLogin(email,password);
	     ResponseEntity<?> responseEntity = new ResponseEntity<>(userDto, HttpStatus.OK);
			return responseEntity;	
	}
	
	

  @GetMapping("/purchaseCourse/{userId}/{courseId}")
  public ResponseEntity<PurchasedCourse> purchaseCourse( @PathVariable int userId ,@PathVariable int courseId)
  {
		PurchasedCourse pc =iUserService.purchaseCourse(userId, courseId);
	    ResponseEntity<PurchasedCourse> responseEntity = new ResponseEntity<>(pc, HttpStatus.OK);
	    return responseEntity;	

 }
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id)
	{
		UserEdu userEdu =iUserService.getUserById(id);
	    ResponseEntity<?> responseEntity = new ResponseEntity<>(userEdu, HttpStatus.FOUND);
	    return responseEntity;	
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody UserEdu user) {
		UserEdu userEdu =iUserService.updateUser(id, user);
	    ResponseEntity<?> responseEntity = new ResponseEntity<>(userEdu, HttpStatus.FOUND);
	    return responseEntity;	
		
	}
	
	
	
	
}
