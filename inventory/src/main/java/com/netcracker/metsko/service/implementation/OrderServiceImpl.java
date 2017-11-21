package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.service.OrderService;


import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDaoImpl orderDao;

    @Autowired
    public OrderDaoImpl getOrderDaoImpl()
    {
        return new OrderDaoImpl();
    }


    @Transactional
    public void createOrder(Order order) {
        orderDao.create(order);
    }

    @Transactional
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Transactional
    public Order findOrderById(Long id) {
        return orderDao.read(id);
    }

    @Transactional
    public Order updateOrder(Order order) {
        return orderDao.update(order);
    }

    @Transactional
    public void addOrderItem(Long id, OrderItem orderItem) {
        Order order =orderDao.read(id);
        order.addOrderItem(orderItem);
        orderDao.update(order);
    }

    @Transactional
    public void removeOrderItem(Long id, OrderItem orderItem) {
        Order order = orderDao.read(id);
        order.removeOrderItem(orderItem);
        orderDao.update(order);
    }

    @Transactional
    public void deleteOrder(Order order) {
        orderDao.delete(order);
    }
}
