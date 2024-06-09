
package com.eduPortal.education_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduPortal.education_portal.entity.Course;
import com.eduPortal.education_portal.entity.UserEdu;
import com.eduPortal.education_portal.service.ICourseService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/course")

public class CourseController {

	@Autowired
	private ICourseService iCourseService;

	@PostMapping("/add")
	public ResponseEntity<?> addCourse(@RequestBody @Valid Course course) {
		String str = iCourseService.RegisterCourse(course);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(str, HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCourse(@PathVariable int id, @RequestBody @Valid Course course) {
		
		Course crs = iCourseService.updateCourse(id, course);
		if (crs != null) {
			ResponseEntity<?> responseEntity = new ResponseEntity<>("Data Updated Succesfilly !!", HttpStatus.OK);
			return responseEntity;
		}
		ResponseEntity<?> responseEntity = new ResponseEntity<>("Changes Not Saved.Try Again !!",
				HttpStatus.NOT_ACCEPTABLE);
		return responseEntity;
	}

	@GetMapping("/get/byId/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable int id) {

		Course course = iCourseService.getCourseById(id);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(course, HttpStatus.FOUND);
		return responseEntity;
	}
	
	@GetMapping("/get/byName/course/{title}")
	public ResponseEntity<List<Course> > getCourseByName(@PathVariable String title) {
		List<Course> courses = iCourseService.getCourseByName(title);
		
		ResponseEntity<List<Course> >responseEntity = new ResponseEntity<>(courses, HttpStatus.FOUND);
		return responseEntity;

}
	

}
