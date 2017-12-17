package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.OrderDao;
import com.netcracker.metsko.dao.OrderItemDao;
import com.netcracker.metsko.entity.ExceptionMessage;
import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.entity.enums.Status;
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
            order.setFields();
            orderDao.create(order);
        } catch (Exception e) {
            throw new NotCreatedException("The Order" + ExceptionMessage.NOT_CREATED);
        }
    }

    @Transactional
    public List<Order> findAll() throws NotFoundException, SQLException {
        try {
            List<Order> orderList = orderDao.findAll();
            if (orderList != null) {
                return orderList;
            } else {
                throw new NotFoundException("Orders " + ExceptionMessage.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new NotFoundException("Orders " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public List<Order> findCustomerOrders(String email) throws SQLException, NotFoundException {
        try {
            List<Order> orderList = orderDao.findCustomerOrders(email);
            if (orderList != null) {
                return orderList;
            } else {
                throw new NotFoundException("Customer's orders" + ExceptionMessage.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new NotFoundException("Customer's orders" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Order findOrderById(Long id) throws SQLException, NotFoundException {
        try {
            Order order = (Order) orderDao.read(id);
            if (order != null) {
                return order;
            } else {
                throw new NotFoundException("The Order" + ExceptionMessage.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new NotFoundException("The Order" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Order updateOrder(Order order) throws SQLException, NotUpdatedException {
        try {
            order.setFields();
            Order updatedOrder = (Order) orderDao.update(order);
            if (updatedOrder != null) {
                return updatedOrder;
            } else {
                throw new NotUpdatedException("The Order" + ExceptionMessage.NOT_UPDATED);
            }
        } catch (Exception e) {
            throw new NotUpdatedException("The Order" + ExceptionMessage.NOT_UPDATED);
        }
    }


    @Transactional
    public void addOrderItem(String customerEmail, Long orderId, OrderItem orderItem) throws SQLException, NotUpdatedException {
        try {
            Order order = (Order) orderDao.findCustomerOrdersAndById(customerEmail, orderId);
            if (order != null) {
                orderItemDao.create(orderItem);
                order.addOrderItem(orderItem);
                order.setFields();
                orderDao.update(order);
                orderItemDao.update(orderItem);
            } else {
                throw new NotUpdatedException("OrderItem" + ExceptionMessage.NOT_ADDED);
            }
        } catch (Exception e) {
            throw new NotUpdatedException("OrderItem" + ExceptionMessage.NOT_ADDED);
        }
    }

    @Transactional
    public void removeOrderItem(Long orderId, OrderItem orderItem) throws SQLException, NotUpdatedException {
        try {
            Order order = (Order) orderDao.read(orderId);
            if (order != null) {
                order.removeOrderItem(orderItem);
                order.setFields();
                orderDao.update(order);
            } else {
                throw new NotUpdatedException("OrderItem" + ExceptionMessage.NOT_DELETED);
            }
        } catch (Exception e) {
            throw new NotUpdatedException("OrderItem" + ExceptionMessage.NOT_DELETED);
        }
    }

    @Transactional
    public void deleteOrder(Long id) throws SQLException, NotDeletedException {
        try {
            orderDao.delete(id);
        } catch (Exception e) {
            throw new NotDeletedException("The order " + ExceptionMessage.NOT_DELETED);
        }
    }

    @Override
    public List<Order> findPaidOrders(String customerEmail) throws SQLException, NotFoundException {
        try {
            List<Order> order =  orderDao.findPaidOrders(customerEmail);
            if (order != null) {
                return order;
            } else {
                throw new NotFoundException("The orders" + ExceptionMessage.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new NotFoundException("The orders " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public List<Order> findUnpaidOrders(String customerEmail) throws SQLException, NotFoundException {
        try {
            List<Order> order =  orderDao.findUnpaidOrders(customerEmail);
            if (order != null) {
                return order;
            } else {
                throw new NotFoundException("The orders" + ExceptionMessage.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new NotFoundException("The orders " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public List<Order> findOrdersByStatus(String customerEmail, String status) throws SQLException, NotFoundException {
        try {
            List<Order> order =  orderDao.findOrdersByStatus(customerEmail, status);
            if (order != null) {
                return order;
            } else {
                throw new NotFoundException("The orders" + ExceptionMessage.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new NotFoundException("The orders " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public Double findTotalPrice(String customerEmail, Long orderId) throws SQLException, NotFoundException {
        try {
            Order order =  orderDao.findCustomerOrdersAndById(customerEmail, orderId);
            if (order != null) {
                return order.getTotalPrice();
            } else {
                throw new NotFoundException("The total price" + ExceptionMessage.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new NotFoundException("The total price " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public Order payForOrder(String customerEmail, Long id, Double sumToPay) throws SQLException, NotUpdatedException {
        try {
            Order order = orderDao.findCustomerOrdersAndById(customerEmail, id);
            if (sumToPay==order.getTotalPrice()) {
                if (!order.getStatus().equals(String.valueOf(Status.CANCELED))) {
                    order.setSignPayment();
                    order.setFields();
                    return order;
                } else {
                    throw new NotUpdatedException("The order is canceled");
                }
            }
            else{
                throw new NotUpdatedException("There is not enough money");
            }
        }catch (Exception e)
        {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }
    }
}
