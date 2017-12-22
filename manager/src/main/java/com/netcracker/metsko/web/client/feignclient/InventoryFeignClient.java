package com.netcracker.metsko.web.client.feignclient;

import com.netcracker.metsko.entity.OrderDTO;
import com.netcracker.metsko.entity.OrderItemDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryFeignClient {

    @PostMapping(value = "/customerEmail/{customerEmail}/orders")
    OrderDTO createOrder(@PathVariable("customerEmail") String customerEmail);

    @PostMapping(value = "/customerEmail/{customerEmail}/orders/{id}/orderItems")
    OrderDTO addOrderItem(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id, @RequestBody OrderItemDTO orderItemDTO);

    @GetMapping(value = "/customerEmail/{customerEmail}/orders")
    List<OrderDTO> findCustomerOrders(@PathVariable("customerEmail") String customerEmail);

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/paidOrders")
    List<OrderDTO> findPaidOrders(@PathVariable("customerEmail") String customerEmail);

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/unpaidOrders")
    List<OrderDTO> findUnpaidOrders(@PathVariable("customerEmail") String customerEmail);

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/totalprice")
    Double getTotalPrice(@PathVariable("customerEmail") String customerEmail);

    @PutMapping(value = "/customerEmail/{customerEmail}/orders/{id}")
    OrderDTO payForOrder(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id, @RequestBody Double sumToPay);

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/status/{status}")
    List<OrderDTO> findOrdersByStatus(@PathVariable("customerEmail") String customerEmail, @PathVariable("status") String status);

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/{id}")
    OrderDTO findOrderById(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id);

}
