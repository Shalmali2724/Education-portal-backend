package com.eduPortal.education_portal.controller;

import com.eduPortal.education_portal.entity.Cart;
import com.eduPortal.education_portal.service.ICartService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ICartService cartService;

	//Method to add course to cart
	@PostMapping("/add/{userId}/{courseId}")
	public ResponseEntity<Cart> addCourseToCart(@PathVariable int userId, @PathVariable int courseId) {
		Cart newCart = cartService.addCourseToCart(userId, courseId);
		return new ResponseEntity<>(newCart, HttpStatus.CREATED);
	}
	
	//This method return List of cart Which is used by user for purchasing the course
	@GetMapping("/get/byUserId/{userId}")
	public ResponseEntity<List<Cart>> getCartDetailsByUserId(@PathVariable int userId)
	{
		List<Cart> list=cartService.getCartDetailsByUserId(userId);
		return new ResponseEntity<List<Cart>>(list, HttpStatus.FOUND);
	}

	@PutMapping("/update/{cartId}")
	public ResponseEntity<Cart> updateCart(@PathVariable int cartId, @RequestBody Cart cart) {
		Cart updatedCart = cartService.updateCart(cartId, cart);
		return new ResponseEntity<>(updatedCart, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{cartId}")
	public ResponseEntity<Void> deleteCart(@PathVariable int cartId) {
		cartService.deleteCart(cartId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/get/byId/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable int cartId) {
		Cart cart = cartService.getCartById(cartId);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

}
