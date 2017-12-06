package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface OrderService {

    void createOrder(Order order) throws SQLException, NotCreatedException;

    List<Order> findAll() throws SQLException, NotFoundException;

    List<Order> findCustomerOrders(String email) throws SQLException, NotFoundException;

    Order findOrderById(Long id) throws SQLException, NotFoundException;

    Order updateOrder(Order order) throws SQLException, NotUpdatedException;

    void addOrderItem(Long orderId, OrderItem orderItem) throws SQLException, NotUpdatedException;

    void removeOrderItem(Long orderId,OrderItem orderItem) throws SQLException, NotUpdatedException;

    void deleteOrder(Long id) throws SQLException, NotDeletedException;
}
