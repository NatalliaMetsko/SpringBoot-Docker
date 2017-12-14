package com.netcracker.metsko.web.client.feignclient;

import com.netcracker.metsko.entity.OfferDTO;
import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderDTO;
import com.netcracker.metsko.entity.OrderItem;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryFeignClient {

    @PostMapping
    void createOrder(@RequestBody Order order);

    /*добавить в OrderService создание OrderItem через OfferDTO*/
    @PutMapping(value = "/{customerEmail}/orders/{id}")
    OrderDTO addOrderItem(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id, @RequestBody OfferDTO offerDTO);

    @GetMapping(value = "/{customerEmail}/orders")
    List<OrderDTO> findAllCustomersOrders(@PathVariable("customerEmail") String customerEmail);

    @GetMapping(value = "/{customerEmail}/orders/paid")
    List<OrderDTO> findPaidOrders(@PathVariable("customerEmail") String customerEmail);

    @GetMapping(value = "/{customerEmail}/orders/unpaid")
    List<OrderDTO> findUnpaidOrders(@PathVariable("customerEmal") String customerEmail);

    @GetMapping(value = "/{customerEmail}/orders/totalprice")
    Double getTotalPrice(@PathVariable("customerEmail") String customerEmail);

    @PutMapping(value = "/{customerEmail}/orders/{id}")
    OrderDTO payForOrder(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id, @RequestBody Double sumToPay);

    @GetMapping(value = "/{customerEmail}/orders/status/{status}")
    List<OrderDTO> findOrdersByStatus(@PathVariable("customerEmail") String customerEmail, @PathVariable("status") String status);

    @GetMapping(value = "/{customerEmail}/orders/{id}")
    OrderDTO findOrderById(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id);

    @PutMapping(value = "/{id}")
    OrderDTO updateOrderByAddingOrRemoving(@PathVariable("id") Long id, @RequestBody OrderItem orderItem); //????
}