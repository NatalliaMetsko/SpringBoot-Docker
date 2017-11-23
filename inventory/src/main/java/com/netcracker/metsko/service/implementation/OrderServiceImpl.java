package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.OrderDao;
import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDao orderDao;

    @Transactional
    public void createOrder(Order order) throws SQLException {
        orderDao.create(order);
    }

    @Transactional
    public List<Order> findAll() throws SQLException {
        return orderDao.findAll();
    }

    @Override
    public List<Order> findCustomerOrders(String email) throws SQLException {
        return orderDao.findCustomerOrders( email);
    }

    @Transactional
    public Order findOrderById(Long id) throws SQLException {
        return (Order) orderDao.read(id);
    }

    @Transactional
    public Order updateOrder(Order order) throws SQLException {
        return (Order) orderDao.update(order);
    }


    @Transactional
    public void addOrderItem(Long orderId, OrderItem orderItem) throws SQLException {
        Order order = (Order) orderDao.read(orderId);
        order.addOrderItem(orderItem);
        orderDao.update(order);
    }

    @Transactional
    public void removeOrderItem(Long orderId, OrderItem orderItem) throws SQLException {
        Order order = (Order) orderDao.read(orderId);
        order.removeOrderItem(orderItem);
        orderDao.update(order);
    }

    @Transactional
    public void deleteOrder(Order order) throws SQLException {
        orderDao.delete(order);
    }
}
