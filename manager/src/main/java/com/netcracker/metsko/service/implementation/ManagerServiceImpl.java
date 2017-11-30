package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.entity.*;
import com.netcracker.metsko.service.ManagerService;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {


    @Override
    public List<Offer> findFilteredOffers(Category category, List<Tag> tagList, Price price) {
        return null;
    }

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public Order addOrderItem(OrderItem orderItem) {
        return null;
    }

    @Override
    public List<Order> findAllCustomersOrders(String customerEmail) {
        return null;
    }

    @Override
    public List<Order> findPaidOrders(String customerEmail, boolean trueSignPayment) {
        return null;
    }

    @Override
    public List<Order> FindUnpaidOrders(String customerEmail, boolean falseSignPayment) {
        return null;
    }

    @Override
    public Double getTotalPrice(String customerEmail) {
        return null;
    }

    @Override
    public Order payForOrder(String customerEmail, Long id, boolean trueSignPayment) {
        return null;
    }

    @Override
    public List<Order> findOrdersByStatus(boolean signPayment) {
        return null;
    }

    @Override
    public Order findOrderById(Long id) {
        return null;
    }

    @Override
    public Order updateOrderByAddingOrRemoving(Long id, OrderItem orderItem) {
        return null;
    }
}
