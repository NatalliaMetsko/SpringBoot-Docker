package com.netcracker.metsko.web.client.feignclient;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryFeignClient {


    void createOrder(Order order);

    Order addOrderItem(OrderItem orderItem);

    List<Order> findAllCustomersOrders(String customerEmail);

    List<Order> findPaidOrders(String customerEmail, boolean trueSignPayment);

    List<Order> findUnpaidOrders(String customerEmail, boolean falseSignPayment);

    Double getTotalPrice(String customerEmail);

    Order payForOrder(String customerEmail, Long id, boolean trueSignPayment);

    List<Order> findOrdersByStatus(boolean signPayment);

    Order findOrderById(Long id);

    Order updateOrderByAddingOrRemoving(Long id, OrderItem orderItem);
}
