package com.monocart.service;

import com.monocart.entity.Order;
import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId);

    List<Order> getOrdersByUserId(Long userId);

    Order updateOrderStatus(Long orderId, String status);
}
