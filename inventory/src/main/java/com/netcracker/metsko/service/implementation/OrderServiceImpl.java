package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.OrderDao;
import com.netcracker.metsko.dao.OrderItemDao;
import com.netcracker.metsko.entity.ExceptionMessage;
import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.entity.enums.Status;
import com.netcracker.metsko.exception.NotCreatedException;
import com.netcracker.metsko.exception.NotDeletedException;
import com.netcracker.metsko.exception.NotFoundException;
import com.netcracker.metsko.exception.NotUpdatedException;
import com.netcracker.metsko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDate;
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
            throw new NotCreatedException("The Order" + ExceptionMessage.NOT_CREATED);
        }
    }

    @Transactional
    public List<Order> findAll() throws NotFoundException, SQLException {
        try {
            List<Order> orderList = orderDao.findAll();
            return orderList;
        } catch (Exception e) {
            throw new NotFoundException("Orders " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public List<Order> findCustomerOrders(String email) throws SQLException, NotFoundException {
        try {
            List<Order> orderList = orderDao.findCustomerOrders(email);
            return orderList;
        } catch (Exception e) {
            throw new NotFoundException("Customer's orders" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Order findOrderById(Long id) throws SQLException, NotFoundException {
        try {
            Order order = (Order) orderDao.read(id);
            return order;
        } catch (Exception e) {
            throw new NotFoundException("The Order" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Order updateOrder(Order order) throws SQLException, NotUpdatedException {
        try {
            Order updatedOrder = (Order) orderDao.update(order);
            return updatedOrder;
        } catch (Exception e) {
            throw new NotUpdatedException("The Order" + ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public Order addOrderItem(Long id, OrderItem orderItem) throws SQLException, NotUpdatedException, NotFoundException {
        try {
            Order order = (Order) orderDao.read(id);
            if (order.getStatus().equals(String.valueOf(Status.PENDING))
                    || order.getStatus().equals(String.valueOf(Status.EMPTY))) {
                orderItemDao.create(orderItem);
                order.addOrderItem(orderItem);
                orderItemDao.update(orderItem);
                return (Order) orderDao.update(order);
            } else {
                throw new NotUpdatedException("The order is not suitable.\n");
            }
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Order removeOrderItem(Long orderId, Long orderItemId) throws SQLException, NotUpdatedException {
        try {
            Order order = (Order) orderDao.read(orderId);
            if (order.getStatus().equals(String.valueOf(Status.PENDING))) {
                orderItemDao.delete(orderItemId);
                if (order.getItemAmount() == 0) {
                    order.setStatus(String.valueOf(Status.EMPTY));
                }
                order.setItemAmount(order.getOrderItemList().size());
                order.setTotalPrice(order.getOrderItemList().stream().mapToDouble(OrderItem::getPrice).sum());
                orderDao.update(order);
                return order;
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
    public List<Order> getOrdersByPayment(boolean signPayment) throws SQLException, NotFoundException {
        try {
            List<Order> order = orderDao.getOrdersByPayment(signPayment);
            return order;
        } catch (Exception e) {
            throw new NotFoundException("The orders " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public List<Order> findOrdersByStatus(String status) throws SQLException, NotFoundException {
        try {
            List<Order> order = orderDao.findOrdersByStatus(status.toUpperCase());
            return order;
        } catch (Exception e) {
            throw new NotFoundException("The orders " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public Double findTotalPrice(Long orderId) throws SQLException, NotFoundException {
        try {
            Order order = (Order) orderDao.read(orderId);
            return order.getTotalPrice();
        } catch (Exception e) {
            throw new NotFoundException("The total price " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Order payForOrder(Long id) throws SQLException, NotUpdatedException {
        try {
            Order order = (Order) orderDao.read(id);
            if (order.getStatus().equals(String.valueOf(Status.PENDING))) {
                order.setSignPayment();
                order.setPaymentDate(LocalDate.now());
                order.setDataOfCompletion();
                order.setStatus(String.valueOf(Status.ACTIVE));
                return (Order) orderDao.update(order);
            } else {
                throw new NotUpdatedException("The order is not available for paying.");
            }
        } catch (Exception e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public Order cancelOrder(Long id) throws SQLException, NotUpdatedException {
        try {
            Order order = (Order) orderDao.read(id);
            order.setStatus(String.valueOf(Status.CANCELED));
            return (Order) orderDao.update(order);
        } catch (Exception e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }
    }
}
