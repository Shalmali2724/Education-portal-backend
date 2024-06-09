package com.eduPortal.education_portal.service;

import java.util.List;

import com.eduPortal.education_portal.entity.Course;

public interface ICourseService {

	public String RegisterCourse(Course course);
	public String RemoveCourse(int id);
	public Course updateCourse(int id,Course course);
	public Course getCourseById(int id);
	public List<Course> getCourseByName(String courseName);
	public List<Course> getCourseByAdminId(int id);
	public String deleteCourseByCourseId(int id);
}
