package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.ExceptionMessage;
import com.netcracker.metsko.entity.Order;
import com.netcracker.metsko.entity.OrderItem;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/inventory/orders")
@Api(value = "Controller", description = "This is order controller")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController() {
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST",
            value = "Create an order",
            response = Order.class,
            nickname = "createOrder")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Order created"),
            @ApiResponse(code = 500, message = "Order not created")
    })
    public ResponseEntity<Order> createOrder(@Validated @RequestBody Order order) throws NotCreatedException, SQLException {
        if (order.getCustomerEmail().length() != 0) {
            orderService.createOrder(order);
            return new ResponseEntity<Order>(order, HttpStatus.CREATED);
        } else {
            throw new NotCreatedException(ExceptionMessage.NULL_FIELDS);
        }
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET",
            value = "Find all orders",
            response = Order.class,
            nickname = "findAll",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Orders found"),
            @ApiResponse(code = 404, message = "Orders not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Order>> findAll() throws SQLException, NotFoundException {
        try {
            List<Order> orderList = orderService.findAll();
            return new ResponseEntity<List<Order>>(orderList, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders")
    @ApiOperation(httpMethod = "GET",
            value = "Find an order by it's customerEmail",
            response = Order.class,
            nickname = "findCustomerOrders")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Orders found"),
            @ApiResponse(code = 404, message = "Orders not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Order>> findCustomerOrders(@PathVariable("email") String customerEmail) throws NotFoundException, SQLException {
        try {
            List<Order> orderList = orderService.findCustomerOrders(customerEmail);
            return new ResponseEntity<List<Order>>(orderList, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(httpMethod = "GET",
            value = "Find an order by it's id",
            response = Order.class,
            nickname = "findByName")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Order found"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Order> findById(@PathVariable("id") Long id) throws NotFoundException, SQLException {
        try {
            Order order = orderService.findOrderById(id);
            return new ResponseEntity<Order>(order, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @PutMapping
    @ApiOperation(httpMethod = "PUT",
            value = "Update order",
            response = Order.class,
            nickname = "updateOrder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order updated"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 500, message = "Order not updated")
    })
    public ResponseEntity<Order> updateOrder(@Validated @RequestBody Order order) throws NotUpdatedException, SQLException {
        try {
            if (order.getCustomerEmail().length() != 0 && orderService.findOrderById(order.getId()) != null) {
                Order updatedOrder = orderService.updateOrder(order);
                return new ResponseEntity<Order>(order, HttpStatus.OK);
            } else {
                throw new NotUpdatedException(ExceptionMessage.NULL_FIELDS);
            }
        } catch (NotFoundException e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }

    }

    @PutMapping(value = "/customerEmail/{customerEmail}/orders/{id}/addItems")
    @ApiOperation(httpMethod = "PUT",
            value = "Add orderItem to order",
            response = Long.class,
            nickname = "addOrderItem")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OrderItem added"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> addOrderItem(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id, @Validated @RequestBody OrderItem orderItem) throws NotUpdatedException, SQLException {
        if (orderItem.getName().length() != 0) {
            orderService.addOrderItem(customerEmail, id, orderItem);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } else {
            throw new NotUpdatedException(ExceptionMessage.NOT_ADDED);
        }
    }

    @PutMapping(value = "/{id}/remove")
    @ApiOperation(httpMethod = "PUT",
            value = "Remove an orderItem from order",
            response = Long.class,
            nickname = "removeOrderItem")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OrderItem removed"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> removeOrderItem(@PathVariable("id") Long id, @Validated @RequestBody OrderItem orderItem) throws NotUpdatedException, SQLException {
        try {
            orderService.removeOrderItem(id, orderItem);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(httpMethod = "DELETE",
            value = "Delete an order by it's id",
            response = Long.class,
            nickname = "deleteOrder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order deleted"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> deleteOrder(@PathVariable("id") Long id) throws NotDeletedException, SQLException {
        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotDeletedException(ExceptionMessage.NOT_DELETED);
        }
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/paidOrders")
    @ApiOperation(httpMethod = "GET",
            value = "Find paid orders",
            response = Order.class,
            nickname = "findPaidOrders")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Paid orders found"),
            @ApiResponse(code = 404, message = "Paid orders not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Order>> findPaidOrders(@PathVariable("customerEmail") String customerEmail) throws NotFoundException, SQLException {
        try {
            List<Order> orders = orderService.findPaidOrders(customerEmail);
            return new ResponseEntity<List<Order>>(orders, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/unpaidOrders")
    @ApiOperation(httpMethod = "GET",
            value = "Find unpaid orders",
            response = Order.class,
            nickname = "findUnPaidOrders")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Unpaid orders found"),
            @ApiResponse(code = 404, message = "Unpaid orders not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Order>> findUnpaidOrders(@PathVariable("customerEmail") String customerEmail) throws NotFoundException, SQLException {
        try {
            List<Order> orders = orderService.findUnpaidOrders(customerEmail);
            return new ResponseEntity<List<Order>>(orders, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{customerEmail}/orders/status/{status}")
    public ResponseEntity<List<Order>> findOrdersByStatus(@PathVariable("customerEmail") String customerEmail, @PathVariable("status") String status) throws NotFoundException, SQLException {
        try {
            List<Order> orders = orderService.findOrdersByStatus(customerEmail, status);
            return new ResponseEntity<List<Order>>(orders, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/{id}/totalprice")
    @ApiOperation(httpMethod = "GET",
            value = "Find total price of order",
            response = Long.class,
            nickname = "findTotalPrice")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Total price found"),
            @ApiResponse(code = 404, message = "Total price not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Double> findTotalPrice(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id) throws NotFoundException, SQLException {
        try {
            Double totalPrice = orderService.findTotalPrice(customerEmail, id);
            return new ResponseEntity<Double>(totalPrice, HttpStatus.OK);
        } catch (Exception e){
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @PutMapping(value = "/customerEmail/{customerEmail}/orders/{id}")
    @ApiOperation(httpMethod = "PUT",
            value = "Pay total price of the order",
            response = Long.class,
            nickname = "payForOrder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The order is paid"),
            @ApiResponse(code = 404, message = "The order is not paid"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Order> payForOrder(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id, @NotNull @RequestBody Double sumToPay) throws SQLException, NotUpdatedException{
        try {
            Order order = orderService.payForOrder(customerEmail,id, sumToPay);
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        }catch (Exception e){
            throw new NotUpdatedException("The order is not paid.");
        }
    }
}
