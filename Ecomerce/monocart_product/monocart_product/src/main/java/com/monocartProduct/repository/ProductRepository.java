package com.monocartProduct.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.monocartProduct.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

