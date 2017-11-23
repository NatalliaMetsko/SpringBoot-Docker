package com.netcracker.metsko.dao;


import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {

    List<Order> findAll() throws SQLException;

    List<Order> findCustomerOrders(String email) throws SQLException;
}
