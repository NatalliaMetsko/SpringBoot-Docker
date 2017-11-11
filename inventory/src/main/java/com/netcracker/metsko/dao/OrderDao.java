package com.netcracker.metsko.dao;


import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;

import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {

    List<Order> findAll();

    List<OrderItem> findOrderItemByOrder(Order order);
}
