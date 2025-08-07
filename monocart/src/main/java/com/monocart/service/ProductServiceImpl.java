package com.monocart.service;

import com.monocart.entity.Product;
import com.monocart.repository.ProductRepository;
import com.monocart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product p = existing.get();
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setCategory(product.getCategory());
            p.setPrice(product.getPrice());
            p.setStock(product.getStock());
            return productRepository.save(p);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> searchByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
}
