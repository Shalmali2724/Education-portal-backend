package com.eduPortal.education_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduPortal.education_portal.entity.PurchasedCourse;

@Repository
public interface PurchaseCourseRepository extends JpaRepository<PurchasedCourse, Integer>{

}
