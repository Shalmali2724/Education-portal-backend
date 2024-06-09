package com.eduPortal.education_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduPortal.education_portal.entity.UserEdu;

@Repository
public interface UserRepository extends JpaRepository<UserEdu, Integer> {

	Optional<UserEdu> findByEmail(String email);
	Optional<UserEdu> findByEmailAndPassword(String email, String password);
}
