package com.eduPortal.education_portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduPortal.education_portal.entity.Admin;
import com.eduPortal.education_portal.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	Optional<List<Course>> findByTitle(String title);
	Optional<List<Course>> findByAdminId(int adminId);
	
}
