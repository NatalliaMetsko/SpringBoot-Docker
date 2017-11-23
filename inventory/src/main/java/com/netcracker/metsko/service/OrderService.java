package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    void createOrder(Order order) throws SQLException;

    List<Order> findAll() throws SQLException;

    List<Order> findCustomerOrders(String email) throws SQLException;

    Order findOrderById(Long id) throws SQLException;

    Order updateOrder(Order order) throws SQLException;

    void addOrderItem(Long orderId, OrderItem orderItem) throws SQLException;

    void removeOrderItem(Long orderId,OrderItem orderItem) throws SQLException;

    void deleteOrder(Order order) throws SQLException;
}
