package com.netcracker.metsko.web.client.feignclient;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryFeignClient {

    @PostMapping
    void createOrder(@RequestBody Order order);

    @PutMapping(value = "/{id}")
    Order addOrderItem(@PathVariable("id") Long id,@RequestBody OrderItem orderItem);

    @GetMapping(value = "/customerEmail/{customerEmail}")
    List<Order> findAllCustomersOrders(String customerEmail);

    @GetMapping
    List<Order> findPaidOrders(String customerEmail, boolean trueSignPayment);

    @GetMapping
    List<Order> findUnpaidOrders(String customerEmail, boolean falseSignPayment);

    @GetMapping
    Double getTotalPrice(String customerEmail);

    @PutMapping(value = "/customerEmail/{customerEmail}/{id}")
    Order payForOrder(@PathVariable("customerEmail") String customerEmail,@PathVariable("id") Long id, boolean trueSignPayment);

    @GetMapping
    List<Order> findOrdersByStatus(boolean signPayment);

    @GetMapping(value = "/{id}")
    Order findOrderById(@PathVariable("id") Long id);

    @PutMapping(value = "/{id}")
    Order updateOrderByAddingOrRemoving(@PathVariable("id") Long id, @RequestBody OrderItem orderItem);
}
