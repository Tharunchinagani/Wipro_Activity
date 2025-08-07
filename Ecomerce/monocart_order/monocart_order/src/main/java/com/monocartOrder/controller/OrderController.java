package com.monocartOrder.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.monocartOrder.model.Order;
import com.monocartOrder.model.Product;
import com.monocartOrder.model.User;
import com.monocartOrder.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        User user = restTemplate.getForObject(
                "http://monocart-user/users/" + order.getUser().getId(),
                User.class
        );
        order.setUser(user);

        List<Product> Products = new ArrayList<>();
        for (Product p : order.getProducts()) {
            Product product = restTemplate.getForObject(
                    "http://monocart-product/products/" + p.getId(),
                    Product.class
            );
            Products.add(product);
        }
        order.setProducts(Products);

        return order;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
