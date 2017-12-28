package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.exception.NotCreatedException;
import com.netcracker.metsko.exception.NotDeletedException;
import com.netcracker.metsko.exception.NotFoundException;
import com.netcracker.metsko.exception.NotUpdatedException;
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

    Order addOrderItem(Long id, OrderItem orderItem) throws SQLException, NotUpdatedException, NotFoundException;

    Order removeOrderItem(Long orderId, Long orderItemId) throws SQLException, NotUpdatedException;

    void deleteOrder(Long id) throws SQLException, NotDeletedException;

    List<Order> getOrdersByPayment(boolean signPayment) throws SQLException, NotFoundException;

    List<Order> findOrdersByStatus( String status) throws SQLException, NotFoundException;

    Double findTotalPrice( Long orderId) throws SQLException, NotFoundException;

    Order payForOrder( Long id) throws SQLException, NotUpdatedException;

    Order cancelOrder(Long id) throws SQLException, NotUpdatedException;
}
