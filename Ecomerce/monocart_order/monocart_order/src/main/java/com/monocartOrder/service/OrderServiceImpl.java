package com.monocartOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocartOrder.model.Order;
import com.monocartOrder.model.Product;
import com.monocartOrder.model.User;
import com.monocartOrder.repository.OrderRepository;
import com.monocartOrder.repository.ProductRepository;
import com.monocartOrder.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order createOrder(Order order) {
        // Fetch the User
        Long userId = order.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Fetch each Product
        List<Product> attachedProducts = order.getProducts().stream()
                .map(p -> productRepository.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + p.getId())))
                .toList();

        // Calculate total
        double totalAmount = attachedProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        // Set user and products
        order.setUser(user);
        order.setProducts(attachedProducts);
        order.setTotalAmount(totalAmount);

        // Set status only if not provided
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PLACED");
        }

        // Set order date if not provided
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }

    @Override
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setOrderDate(updatedOrder.getOrderDate());
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}



