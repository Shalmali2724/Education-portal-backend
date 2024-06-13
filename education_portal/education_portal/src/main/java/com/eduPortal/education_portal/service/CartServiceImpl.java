package com.eduPortal.education_portal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduPortal.education_portal.entity.Cart;
import com.eduPortal.education_portal.entity.Course;
import com.eduPortal.education_portal.entity.UserEdu;
import com.eduPortal.education_portal.exception.CartGetException;
import com.eduPortal.education_portal.exception.CourseAlreadyExistException;
import com.eduPortal.education_portal.exception.CourseNotFoundException;
import com.eduPortal.education_portal.exception.UserNotFoundException;
import com.eduPortal.education_portal.repository.CartRepository;
import com.eduPortal.education_portal.repository.CourseRepository;
import com.eduPortal.education_portal.repository.UserRepository;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
    private CartRepository cartRepository;
	@Autowired
    private UserRepository userRepository;
	
	   @Autowired
	   private CourseRepository courseRepository;
	
    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }
    
    public Cart addCourseToCart(int userId, int courseId) {
        UserEdu user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found"));
        Optional<Cart> optionalCart=cartRepository.findByCourse(course);
        
      if(optionalCart.isPresent()) {
    	  Cart existingCart = optionalCart.get();
          int existingCartUserId = existingCart.getUser().getId();
          if (existingCartUserId == userId) {
              throw new CourseAlreadyExistException("You Have Already Added this Course To Cart !!");
          }
      }
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCourse(course);
       return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(int cartId, Cart cart) {
        if (cartRepository.existsById(cartId)) {
            cart.setCartId(cartId);
            return cartRepository.save(cart);
        } else {
            throw new CartGetException("Cart not found with id: " + cartId);
        }
    }

    @Override
    public void deleteCart(int cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
        } else {
            throw new CartGetException("Cart not found with id: " + cartId);
        }
    }

    @Override
    public Cart getCartById(int cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            throw new CartGetException("Cart not found with id: " + cartId);
        }

	
    }

	@Override
	public List<Cart> getCartDetailsByUserId(int userId) {
	
        UserEdu user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
               Optional<List<Cart>> list=cartRepository.findByUser(user);
               if(list.isEmpty())
               {
            	   throw new CartGetException("Cart is Empty ");
            	   
               }
		return list.get();
	}

	@Override
	public String deleteCoursefromCart(int courseId) {
Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found"));
  Optional<Cart> optionalCart =cartRepository.findByCourse(course);
  if(optionalCart.isEmpty())
  {
	  throw new CourseNotFoundException("Course not found");
  }
  
  cartRepository.delete(optionalCart.get());

		
		
		return "Course Removed From cart !! ";
	}
    
    
    public String DeleteFromCart(int userId) {
    	
        UserEdu user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
             List<Cart> list=cartRepository.findByUser(user).orElseThrow(()->new UserNotFoundException("dont have any course In Cart"));
//             for(ls:list) {
//            	 cartRepository.delete(null);
//             }
             
             cartRepository.deleteAll(list);
             
    	
		return "removed succesfully";

    }

	
}
