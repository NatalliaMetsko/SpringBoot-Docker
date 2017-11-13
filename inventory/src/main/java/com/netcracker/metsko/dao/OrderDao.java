package com.netcracker.metsko.dao;


import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order, ID> {

    List<Order> findAll() throws SQLException;

    List<OrderItem> findOrderItemByOrder(Order order) throws SQLException;
}
