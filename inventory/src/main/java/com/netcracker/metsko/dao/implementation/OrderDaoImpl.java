package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.OrderDao;
import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order, Long> implements OrderDao {


    public OrderDaoImpl() {
    }

    @Override
    public List<Order> findAll() throws SQLException {
        return entityManager.createQuery("select io from InvOrder  io", Order.class).getResultList();
    }

    @Override
    public List<Order> findCustomerOrders(String email) throws SQLException {
        return entityManager.createQuery("select io from InvOrder io where io.customerEmail = '" + email + "'", Order.class).getResultList();
    }

    @Override
    public Order findCustomerOrdersAndById(String customerEmail, Long orderId) {
        return entityManager.createQuery("select io from  InvOrder io where io.customerEmail = '" + customerEmail+"' and io.id="+orderId, Order.class).getSingleResult();
    }

    @Override
    public List<Order> findPaidOrders(String customerEmail) throws SQLException, NotFoundException {
        return entityManager.createQuery("select io from InvOrder io where io.customerEmail='"+customerEmail+"' and io.signPayment="+true, Order.class).getResultList();
    }

    @Override
    public List<Order> findUnpaidOrders(String customerEmail) throws SQLException, NotFoundException {
        return entityManager.createQuery("select io from InvOrder io where io.customerEmail='"+customerEmail+"' and io.signPayment="+false, Order.class).getResultList();
    }

    @Override
    public List<Order> findOrdersByStatus(String customerEmail, String status) throws SQLException, NotFoundException {
        return entityManager.createQuery("select io from InvOrder io where io.customerEmail='"+customerEmail+"' and io.status='"+status+"'", Order.class).getResultList();
    }

}
