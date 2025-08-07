package com.monocartCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.monocartCart.model.Cart;
import com.monocartCart.model.Product;
import com.monocartCart.model.User;
import com.monocartCart.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        User user = restTemplate.getForObject("http://monocart-user/users/" + cart.getUser().getId(), User.class);
        cart.setUser(user);
        Product product = restTemplate.getForObject("http://monocart-product/products/" + cart.getProduct().getId(), Product.class);
        cart.setProduct(product);

        return cart;
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
