package com.monocartOrder.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.monocartOrder.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

