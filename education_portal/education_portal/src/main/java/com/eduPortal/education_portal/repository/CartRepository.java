package com.eduPortal.education_portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduPortal.education_portal.entity.Cart;
import com.eduPortal.education_portal.entity.UserEdu;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
	  Optional<List<Cart>> findByUser(UserEdu user);
	  
}
