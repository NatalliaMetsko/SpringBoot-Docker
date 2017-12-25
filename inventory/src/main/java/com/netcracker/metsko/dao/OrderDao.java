package com.netcracker.metsko.dao;


import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.exceptions.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {

    List<Order> findAll() throws SQLException;

    List<Order> findCustomerOrders(String email) throws SQLException;

    List<Order> getOrdersByPayment(boolean signPayment)throws SQLException, NotFoundException;

    List<Order> findOrdersByStatus(String status) throws SQLException, NotFoundException;
}
