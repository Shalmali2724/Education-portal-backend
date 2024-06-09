package com.eduPortal.education_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduPortal.education_portal.dto.AdminDto;
import com.eduPortal.education_portal.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByEmailId(String emailId);
	Optional<Admin> findByEmailIdAndPassword(String emailId, String password);
	
}
