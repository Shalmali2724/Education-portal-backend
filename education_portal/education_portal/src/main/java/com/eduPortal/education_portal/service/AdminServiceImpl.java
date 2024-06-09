package com.eduPortal.education_portal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduPortal.education_portal.dto.AdminDto;
import com.eduPortal.education_portal.dto.UserDto;
import com.eduPortal.education_portal.entity.Admin;
import com.eduPortal.education_portal.entity.UserEdu;
import com.eduPortal.education_portal.exception.AdminNotFoundException;
import com.eduPortal.education_portal.exception.UserNotFoundException;
import com.eduPortal.education_portal.repository.AdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public String registerAdmin(Admin admin) {
		Optional<Admin> optionalAdmin=adminRepository.findByEmailId(admin.getEmailId());
		if(optionalAdmin.isEmpty()) {	
		Admin newAdmin=adminRepository.save(admin);
		return "You have Registered Succesfully";
		}
		return "You have Already Register!! Please Login";
	}

	@Override
	public AdminDto getAdminById(int id) {
		
		
		Optional<Admin> admin=adminRepository.findById(id);
		if(admin.isEmpty())
		{
			throw new AdminNotFoundException("Admin Not Found with this Email Id");
		}
		Admin newAdmin=admin.get();
		AdminDto adminDto=new AdminDto();
		
		adminDto.setId(id);
		adminDto.setAdminName(newAdmin.getAdminName());
		adminDto.setContact(newAdmin.getContact());
		adminDto.setEmailId(newAdmin.getEmailId());
		return adminDto;


	}
	
	
	@Override
	public AdminDto adminLogin(String emailId, String password) {

		Optional<Admin> optionalAdmin = adminRepository.findByEmailIdAndPassword(emailId, password);
		if (optionalAdmin.isEmpty()) {
			throw new AdminNotFoundException("Enter Valid EmailId & Password");
		}
		Admin admin = optionalAdmin.get();
		AdminDto adminDto=new AdminDto();
		

		adminDto.setAdminName(admin.getAdminName());
		adminDto.setContact(admin.getContact());
		adminDto.setEmailId(admin.getEmailId());
		adminDto.setId(admin.getId());

		return adminDto;
	}

}
