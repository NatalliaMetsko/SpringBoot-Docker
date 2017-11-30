package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/inventory/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController() {
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws NotCreatedException, SQLException{
        orderService.createOrder(order);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() throws SQLException, NotFoundException{
        List<Order> orderList = orderService.findAll();
        return new ResponseEntity<List<Order>>(orderList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<List<Order>> findCustomerOrders(@PathVariable("email") String customerEmail) throws NotFoundException, SQLException{
        List<Order> orderList = orderService.findCustomerOrders(customerEmail);
        return new ResponseEntity<List<Order>>(orderList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id) throws NotFoundException, SQLException{
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<Order>(order, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) throws NotUpdatedException, SQLException{
        Order updatedOrder = orderService.updateOrder(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Long> addOrderItem(@PathVariable("id") Long id, @RequestBody OrderItem orderItem) throws NotUpdatedException, SQLException
    {
        orderService.addOrderItem(id, orderItem);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Long> removeOrderItem(@PathVariable("id") Long id, @RequestBody OrderItem orderItem)throws NotUpdatedException, SQLException{
        orderService.removeOrderItem(id, orderItem);
        return new ResponseEntity<Long>(id,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable("id") Long id)throws NotDeletedException, SQLException{
        orderService.deleteOrder(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
