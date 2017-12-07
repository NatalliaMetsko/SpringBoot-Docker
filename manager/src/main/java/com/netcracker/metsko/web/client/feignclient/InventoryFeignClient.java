package com.netcracker.metsko.web.client.feignclient;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderDTO;
import com.netcracker.metsko.entity.OrderItem;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryFeignClient {

    @PostMapping
    void createOrder(@RequestBody Order order);

    @PutMapping(value = "/{id}")
    OrderDTO addOrderItem(@PathVariable("id") Long id, @RequestBody OrderItem orderItem);

    @GetMapping(value = "/customerEmail/{customerEmail}")
    List<OrderDTO> findAllCustomersOrders(String customerEmail);

    @GetMapping
    List<OrderDTO> findPaidOrders(String customerEmail, boolean trueSignPayment);

    @GetMapping
    List<OrderDTO> findUnpaidOrders(String customerEmail, boolean falseSignPayment);

    @GetMapping
    Double getTotalPrice(String customerEmail);

    @PutMapping(value = "/customerEmail/{customerEmail}/{id}")
    OrderDTO payForOrder(@PathVariable("customerEmail") String customerEmail,@PathVariable("id") Long id, boolean trueSignPayment);

    @GetMapping
    List<OrderDTO> findOrdersByStatus(boolean signPayment);

    @GetMapping(value = "/{id}")
    OrderDTO findOrderById(@PathVariable("id") Long id);

    @PutMapping(value = "/{id}")
    OrderDTO updateOrderByAddingOrRemoving(@PathVariable("id") Long id, @RequestBody OrderItem orderItem);
}
