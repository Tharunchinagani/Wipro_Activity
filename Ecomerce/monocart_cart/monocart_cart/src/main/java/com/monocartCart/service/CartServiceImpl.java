package com.monocartCart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocartCart.model.Cart;
import com.monocartCart.repository.CartRepository;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart updateCart(Long id, Cart updatedCart) {
        Cart cart = getCartById(id);
        if (cart != null) {
            cart.setProduct(updatedCart.getProduct());
            cart.setUser(updatedCart.getUser());
            cart.setQuantity(updatedCart.getQuantity());
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
