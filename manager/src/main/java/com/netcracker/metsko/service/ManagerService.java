package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.*;

import java.util.List;

public interface ManagerService{

    List<Offer> findFilteredOffers(Category category, List<Tag> tagList, Price price);

    void createOrder(Order order);

    Order addOrderItem(OrderItem orderItem);

    List<Order> findAllCustomersOrders(String customerEmail);

    List<Order> findPaidOrders(String customerEmail, boolean trueSignPayment);

    List<Order> FindUnpaidOrders(String customerEmail, boolean falseSignPayment);

    Double getTotalPrice(String customerEmail);

    Order payForOrder(String customerEmail, Long id, boolean trueSignPayment);

    List<Order> findOrdersByStatus(boolean signPayment);

    Order findOrderById(Long id);

    Order updateOrderByAddingOrRemoving(Long id, OrderItem orderItem);
}
