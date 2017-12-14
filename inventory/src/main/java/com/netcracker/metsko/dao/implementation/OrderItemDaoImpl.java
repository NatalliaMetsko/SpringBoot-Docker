package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.OrderItemDao;
import com.netcracker.metsko.entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDaoImpl extends GenericDaoImpl<OrderItem, Long> implements OrderItemDao {
}
