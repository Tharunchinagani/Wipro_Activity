package com.monocart.service;


import com.monocart.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

    List<Product> searchByName(String name);
    List<Product> searchByCategory(String category);
}
