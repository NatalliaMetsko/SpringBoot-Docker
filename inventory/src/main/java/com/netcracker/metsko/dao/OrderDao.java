package com.netcracker.metsko.dao;


import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.exceptions.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {

    List<Order> findAll() throws SQLException;

    List<Order> findCustomerOrders(String email) throws SQLException;

    Order findCustomerOrdersAndById(String customerEmail, Long orderId);

    List<Order> findPaidOrders(String customerEmail)throws SQLException, NotFoundException;

    List<Order> findUnpaidOrders(String customerEmail) throws SQLException, NotFoundException;

    List<Order> findOrdersByStatus(String customerEmail, String status) throws SQLException, NotFoundException;
}
