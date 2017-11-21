package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);

    List<Order> findAll();

    //find all customer orders - ?

    Order findOrderById(Long id);

    Order updateOrder(Order order);

    void addOrderItem(OrderItem orderItem);

    void removeOrderItem(OrderItem orderItem);

    void deleteOrder(Order order);
}
