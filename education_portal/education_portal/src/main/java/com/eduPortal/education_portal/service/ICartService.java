package com.eduPortal.education_portal.service;

import java.util.List;

import com.eduPortal.education_portal.entity.Cart;

public interface ICartService {
	
	Cart addCart(Cart cart);
    Cart updateCart(int cartId, Cart cart);
    void deleteCart(int cartId);
    Cart getCartById(int cartId);
    List<Cart> getCartDetailsByUserId(int userId);
    public Cart addCourseToCart(int userId, int courseId);
}
