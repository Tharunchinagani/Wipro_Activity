package com.monocartCart.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.monocartCart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
