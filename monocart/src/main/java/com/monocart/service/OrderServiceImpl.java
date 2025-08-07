package com.monocart.service;

import com.monocart.entity.Cart;
import com.monocart.entity.Order;
import com.monocart.entity.OrderStatus;
import com.monocart.repository.CartRepository;
import com.monocart.repository.OrderRepository;
import com.monocart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Order placeOrder(Long userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            return null; // no items to order
        }

        double total = cartItems.stream().mapToDouble(Cart::getPrice).sum();

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.NEW);

        cartRepository.deleteAll(cartItems); // clear cart after order

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
            return orderRepository.save(order);
        }
        return null;
    }
}
