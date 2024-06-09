package com.eduPortal.education_portal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduPortal.education_portal.entity.Admin;
import com.eduPortal.education_portal.entity.Course;
import com.eduPortal.education_portal.entity.UserEdu;
import com.eduPortal.education_portal.exception.AdminNotFoundException;
import com.eduPortal.education_portal.exception.CourseNotFoundException;
import com.eduPortal.education_portal.exception.UserNotFoundException;
import com.eduPortal.education_portal.repository.AdminRepository;
import com.eduPortal.education_portal.repository.CourseRepository;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public String RegisterCourse(Course course) {
		Optional<Admin> admin=adminRepository.findById(course.getAdminId());
		if(admin.isEmpty())
		{
			throw new AdminNotFoundException("You dont have access of this Operation");
		}
		courseRepository.save(course);
		return "Course added successfully !!! ";
	}

	@Override
	public String RemoveCourse(int id) {
		
		
		
		
		return null;
	}

	@Override
	public Course updateCourse(int id, Course course) {

		Optional<Admin> admin=adminRepository.findById(course.getAdminId());
		if(admin.isEmpty())
		{
			throw new AdminNotFoundException("You are not registered Admin .You dont have access of this Operation");
		}
		Optional<Course> useCourse=courseRepository.findById(id);
		if(useCourse.isEmpty())
		{
			throw new CourseNotFoundException("Course not registerd with this Id "+id);
		}
		 Course newCourse=useCourse.get();
		 newCourse.setTitle(course.getTitle());	
		 newCourse.setPrice(course.getPrice());
		 newCourse.setDescription(course.getDescription());
		 newCourse.setAdminId(course.getAdminId());
		 newCourse.setCourseId(id);
				 
		return  courseRepository.save(newCourse);
	}

	
	@Override
	public Course getCourseById(int id) {
		 Optional<Course> optionalCourse=courseRepository.findById(id);
		 if(optionalCourse.isEmpty())
		 {
		  throw new CourseNotFoundException("Course not registerd with this Id "+id);
		 }
		 Course course=optionalCourse.get();
		 return course;	
	}

	@Override
	public List<Course> getCourseByName(String title) {
		
		    Optional<List<Course>> optionalCourses = courseRepository.findByTitle(title);
		    if (optionalCourses.isEmpty()) {
		        throw new CourseNotFoundException("Courses with the title \"" + title + "\" are not available");
		    }

		    return optionalCourses.get();
	}
	
	@Override
	public List<Course> getCourseByAdminId(int adminId) {
		
		Optional<Admin> admin=adminRepository.findById(adminId);
		if(admin.isEmpty()) {
			throw new  AdminNotFoundException("Admin Not Found with this Id:"+adminId);
		}
		 Optional<List<Course>> optionalCourses = courseRepository.findByAdminId(adminId);
		    if (optionalCourses.isEmpty()) {
		        throw new CourseNotFoundException("Courses Not added yet !! Thank You ");
		    }

		    return optionalCourses.get();
	}

	@Override
	public String deleteCourseByCourseId(int id) {
	
		Optional<Course> optionalcourse = courseRepository.findById(id);
		if (optionalcourse.isEmpty()) {
			throw new CourseNotFoundException("User not found with id: " + id);
		}
		courseRepository.delete(optionalcourse.get());
		return "Course deleted Sucessfully";
	
	}

}
