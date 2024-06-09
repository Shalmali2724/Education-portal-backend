package com.eduPortal.education_portal.service;

import com.eduPortal.education_portal.dto.AdminDto;
import com.eduPortal.education_portal.dto.UserDto;
import com.eduPortal.education_portal.entity.Admin;
import com.eduPortal.education_portal.entity.UserEdu;

public interface IAdminService {
	public String registerAdmin(Admin admin);
	public AdminDto getAdminById(int id);
	public AdminDto adminLogin(String email, String password);
}
