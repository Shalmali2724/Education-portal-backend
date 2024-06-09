package com.eduPortal.education_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
	private int id;
	private  String adminName;
	private String contact;
	private String emailId;


}
