package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.OrderDao;
import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;


import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order, Long> implements OrderDao {

    @Override
    public List<Order> findAll() throws SQLException {
        return entityManager.createQuery(" from Inv_order io").getResultList();
    }

}
