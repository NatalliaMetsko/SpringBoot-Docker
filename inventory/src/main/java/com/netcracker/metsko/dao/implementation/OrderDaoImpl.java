package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.OrderDao;
import com.netcracker.metsko.entity.Order;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order, Long> implements OrderDao {


    public OrderDaoImpl() {
    }

    @Override
    public List<Order> findAll() throws SQLException {
        return entityManager.createQuery("select io from Inv_order io").getResultList();
    }

    @Override
    public List<Order> findCustomerOrders(String email) throws SQLException {
        return entityManager.createQuery("select io from Inv_order io where io.customerEmail = '"+email+"'",Order.class).getResultList();
    }

}
