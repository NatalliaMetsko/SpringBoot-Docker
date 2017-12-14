package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.OrderDao;
import com.netcracker.metsko.dao.OrderItemDao;
import com.netcracker.metsko.entity.ExceptionMessage;
import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
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

    @Autowired
    private OrderItemDao orderItemDao;

    public OrderServiceImpl() {
    }

    @Transactional
    public void createOrder(Order order) throws NotCreatedException, SQLException {
        try {
            orderDao.create(order);
        } catch (Exception e) {
            throw new NotCreatedException("The order" + ExceptionMessage.NOT_CREATED);
        }
    }

    @Transactional
    public List<Order> findAll() throws NotFoundException, SQLException {
        List<Order> orderList = orderDao.findAll();
        if (orderList != null) {
            return orderList;
        } else {
            throw new NotFoundException("Orders " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public List<Order> findCustomerOrders(String email) throws SQLException, NotFoundException {
        List<Order> orderList = orderDao.findCustomerOrders(email);
        if (orderList != null) {
            return orderList;
        } else {
            throw new NotFoundException("Customer's orders" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Order findOrderById(Long id) throws SQLException, NotFoundException {
        Order order = (Order) orderDao.read(id);
        if (order != null) {
            return order;
        } else {
            throw new NotFoundException("The order" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Order updateOrder(Order order) throws SQLException, NotUpdatedException {
        Order updatedOrder = (Order) orderDao.update(order);
        if (updatedOrder != null) {
            return updatedOrder;
        } else {
            throw new NotUpdatedException("The order" + ExceptionMessage.NOT_UPDATED);
        }
    }


    @Transactional
    public void addOrderItem(Long orderId, OrderItem orderItem) throws SQLException, NotUpdatedException {
        Order order = (Order) orderDao.read(orderId);
        if (order != null) {
            orderItemDao.create(orderItem);
            order.addOrderItem(orderItem);
//            order.setTotalPrice(order.getTotalPrice());
            orderDao.update(order);
            orderItemDao.update(orderItem);
        } else {
            throw new NotUpdatedException("OrderItem" + ExceptionMessage.NOT_ADDED);
        }
    }

    @Transactional
    public void removeOrderItem(Long orderId, OrderItem orderItem) throws SQLException, NotUpdatedException {
        Order order = (Order) orderDao.read(orderId);
        if (order != null) {
            order.removeOrderItem(orderItem);
            orderDao.update(order);
        } else {
            throw new NotUpdatedException("OrderItem" + ExceptionMessage.NOT_DELETED);
        }
    }

    @Transactional
    public void deleteOrder(Long id) throws SQLException, NotDeletedException {
        try {
            orderDao.delete(id);
        } catch (Exception e) {
            throw new NotDeletedException("the order " + ExceptionMessage.NOT_DELETED);
        }
    }
}
