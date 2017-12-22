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

    Order addOrderItem(String customerEmail,Long id, OrderItem orderItem) throws SQLException, NotUpdatedException, NotFoundException;

    Order removeOrderItem(Long orderId, OrderItem orderItem) throws SQLException, NotUpdatedException;

    void deleteOrder(Long id) throws SQLException, NotDeletedException;

    List<Order> findPaidOrders(String customerEmail) throws SQLException, NotFoundException;

    List<Order> findUnpaidOrders(String customerEmail) throws SQLException, NotFoundException;

    List<Order> findOrdersByStatus(String customerEmail, String status) throws SQLException, NotFoundException;

    Double findTotalPrice(String customerEmail, Long orderId) throws SQLException, NotFoundException;

    Order payForOrder(String customerEmail, Long id, Double sumToPay) throws SQLException, NotUpdatedException;

    Order cancelOrder(String customewEmail, Long id) throws SQLException, NotUpdatedException;
}
