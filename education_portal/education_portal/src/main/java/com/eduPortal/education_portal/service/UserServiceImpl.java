package com.eduPortal.education_portal.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eduPortal.education_portal.dto.UserDto;
import com.eduPortal.education_portal.entity.Cart;
import com.eduPortal.education_portal.entity.Course;
import com.eduPortal.education_portal.entity.PurchasedCourse;
import com.eduPortal.education_portal.entity.UserEdu;
import com.eduPortal.education_portal.exception.UserAlreadyRegistredException;
import com.eduPortal.education_portal.exception.UserNotFoundException;
import com.eduPortal.education_portal.repository.CartRepository;
import com.eduPortal.education_portal.repository.PurchaseCourseRepository;
import com.eduPortal.education_portal.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private CartRepository cartRepository;
	@Autowired 
	private CartServiceImpl cartServiceImpl;
	
	@Autowired
	PurchaseCourseRepository purchaseCourseRepository;
	
	@Override
	public UserEdu createUser(UserEdu user) {
		Optional<UserEdu> userEdu = userRepository.findByEmail(user.getEmail());
		if (userEdu.isPresent()) {
			throw new UserAlreadyRegistredException("Employer already Registerd with this email Id !! Please Login ");
		}
		UserEdu newUser = userRepository.save(user);
		return newUser;
	}

	@Override
	public UserDto userLogin(String email, String password) {

		Optional<UserEdu> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("Enter Valid EmailId ");
		}
		
		UserEdu user = optionalUser.get();
		if(!user.getPassword().equals(password))
		{
			throw new UserNotFoundException("Enter Valid  password ");
		}
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUserName(user.getUserName());
		userDto.setEmail(email);

		return userDto;
	}

	@Override
	public UserEdu getUserById(int id) {

		Optional<UserEdu> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not regeisterd with this Id :" + id);
		}
		UserEdu userEdu = user.get();
		return userEdu;

	}

	@Override
	public UserEdu updateUser(int id, UserEdu user) {

		Optional<UserEdu> userEdu = userRepository.findById(id);
		if (userEdu.isEmpty()) {
			throw new UserNotFoundException("User not registerd with this id");
		}
		user.setId(id);
		UserEdu newUser = userEdu.get();
		newUser.setUserName(user.getUserName());
		newUser.setEmail(user.getEmail());
		newUser.setLocation(user.getLocation());
		newUser.setPassword(user.getPassword());
		newUser.setId(id);
		return userRepository.save(newUser);

	}

	@Override
	public String deleteUser(int id) {
		Optional<UserEdu> optionalUser = userRepository.findById(id);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		userRepository.delete(optionalUser.get());
		return "Profile deleted Sucessfully";
	}

	@Override
	public PurchasedCourse purchaseCourse(int userId ) {
		
	List<Cart> list=cartServiceImpl.getCartDetailsByUserId(userId);	
	double totalPrice=0.0;
	ArrayList<Course> arr=new ArrayList<>();
	 for(Cart obj : list) {
	        totalPrice += obj.getCourse().getPrice();    
	        if (!arr.contains(obj.getCourse())) {
	            arr.add(obj.getCourse());
	        }
	 }
		PurchasedCourse pc=new PurchasedCourse();
		pc.setUserId(userId);
		pc.setTotalAmount(totalPrice);
		pc.setCourse(arr);
		purchaseCourseRepository.save(pc);
		cartRepository.deleteAll(list);
		
		return pc;
	 
		
	}

	@Override
	public List<UserEdu> getAllUsers(int pageNumber,int pageSize) {
		PageRequest p= PageRequest.of(pageNumber, pageSize);
		Page<UserEdu> page=userRepository.findAll(p);
		List<UserEdu> list=page.getContent();
		if(list.isEmpty())
		{
			throw new UserNotFoundException("No user Register !!");
		}
		
		return list;
	}

	@Override
	public PurchasedCourse viewPurchaseCourse(int userId) {
		
        UserEdu user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		
		
		return null;
	}

}
