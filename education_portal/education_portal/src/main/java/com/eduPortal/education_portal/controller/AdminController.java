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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduPortal.education_portal.dto.AdminDto;
import com.eduPortal.education_portal.dto.UserDto;
import com.eduPortal.education_portal.entity.Admin;
import com.eduPortal.education_portal.entity.Course;
import com.eduPortal.education_portal.entity.UserEdu;
import com.eduPortal.education_portal.service.IAdminService;
import com.eduPortal.education_portal.service.ICourseService;
import com.eduPortal.education_portal.service.IUserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminService iAdminService;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private ICourseService iCourseService;
	
	//Admin Registration API
	@PostMapping("/register")
	public ResponseEntity<String> registerAdmin( @RequestBody @Valid Admin admin) {
		String msg=iAdminService.registerAdmin(admin);
		ResponseEntity<String> responseEntity=new ResponseEntity<>(msg,HttpStatus.CREATED);	
	  return responseEntity;
	}
	
	
	//Admin login method
	@GetMapping("/login/{emailId}/{password}")
	ResponseEntity<?> adminLogin(String emailId, String password)
	{
		AdminDto adminDto =iAdminService.adminLogin(emailId,password);
	     ResponseEntity<?> responseEntity = new ResponseEntity<>(adminDto, HttpStatus.OK);
			return responseEntity;	
	}
	
	
	//Admin can view All Users Details 
	//used pagination here
	@GetMapping("/get/allUsers")
	public ResponseEntity<List<UserEdu>> getAllUser(
         @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize)
	{
	List<UserEdu> list=iUserService.getAllUsers(pageNumber,pageSize);
	     ResponseEntity<List<UserEdu>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
			return responseEntity;	
	}
	
	
	
	//admin can add course details
	@PostMapping("/course/add")
	public ResponseEntity<?> addCourse(@RequestBody @Valid Course course) {
		String str = iCourseService.RegisterCourse(course);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(str, HttpStatus.CREATED);
		return responseEntity;
	}
	
	//admin can get Course details by title
	@GetMapping("/course/get/byName/{title}")
	public ResponseEntity<?> getCourseByName(@PathVariable String title) {
		List<Course> courses = iCourseService.getCourseByName(title);
		ResponseEntity<?>responseEntity = new ResponseEntity<>(courses, HttpStatus.FOUND);
		return responseEntity;

}
	//admin can delete user account
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser( @PathVariable int id) {
		String str =iUserService.deleteUser(id);
	    ResponseEntity<String> responseEntity = new ResponseEntity<>(str, HttpStatus.OK);
	    return responseEntity;	
	}
	
	@GetMapping("/get/byId/{id}")
	public  ResponseEntity<AdminDto> getAdminById(@PathVariable int id) {	
		AdminDto admin=iAdminService.getAdminById(id);
		ResponseEntity<AdminDto> responseEntity=new ResponseEntity<>(admin,HttpStatus.FOUND);	
	  return responseEntity;
	}
	
	@GetMapping("/get/allCourse/byadminId/{adminId}")
	public ResponseEntity<List<Course>>getCourseByAdminId(@PathVariable  int adminId)
	{
		List<Course> courses = iCourseService.getCourseByAdminId(adminId);
		ResponseEntity<List<Course>>responseEntity = new ResponseEntity<>(courses, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/delete/course/{id}")
	public ResponseEntity<String> deleteCourseByCourseId(@PathVariable int id) {
		
		String str =iCourseService.deleteCourseByCourseId(id);
	    ResponseEntity<String> responseEntity = new ResponseEntity<>(str, HttpStatus.OK);
	    return responseEntity;
	
	
	}
	

}
