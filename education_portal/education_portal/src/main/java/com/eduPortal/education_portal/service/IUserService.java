package com.eduPortal.education_portal.service;

import java.util.List;

import com.eduPortal.education_portal.dto.UserDto;
import com.eduPortal.education_portal.entity.PurchasedCourse;
import com.eduPortal.education_portal.entity.UserEdu;

public interface IUserService {

	UserEdu createUser(UserEdu user);
	UserDto userLogin(String email,String password);
	UserEdu getUserById(int id);
    UserEdu updateUser(int id, UserEdu user);
    String deleteUser(int id);
    PurchasedCourse purchaseCourse( int userId);
    List<UserEdu> getAllUsers(int pageNumber,int pageSize);
	PurchasedCourse viewPurchaseCourse(int userId);

	
}
