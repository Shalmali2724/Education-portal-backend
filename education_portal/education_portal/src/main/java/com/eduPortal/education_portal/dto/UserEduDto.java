package com.eduPortal.education_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEduDto {
	private int id;
	private String userName;
	private String password;
	private String email;
	private String location;
}
