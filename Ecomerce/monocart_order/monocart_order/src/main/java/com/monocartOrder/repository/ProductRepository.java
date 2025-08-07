package com.monocartOrder.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.monocartOrder.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}