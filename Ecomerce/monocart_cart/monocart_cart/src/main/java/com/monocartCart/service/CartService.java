package com.monocartCart.service;


import java.util.List;

import com.monocartCart.model.Cart;

public interface CartService {
    Cart createCart(Cart cart);
    Cart getCartById(Long id);
    List<Cart> getAllCarts();
    Cart updateCart(Long id, Cart cart);
    void deleteCart(Long id);
}

