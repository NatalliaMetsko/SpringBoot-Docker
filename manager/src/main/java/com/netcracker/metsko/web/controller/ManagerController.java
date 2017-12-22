package com.netcracker.metsko.web.controller;


import com.netcracker.metsko.entity.Filter;
import com.netcracker.metsko.entity.OfferDTO;
import com.netcracker.metsko.entity.OrderDTO;
import com.netcracker.metsko.entity.OrderItemDTO;
import com.netcracker.metsko.interceptor.LoggerInterceptor;
import com.netcracker.metsko.web.client.CatalogClient;
import com.netcracker.metsko.web.client.InventoryClient;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/manager")
@Api(value = "Controller", description = "This is manager controller")
public class ManagerController {

    private static final Logger log = Logger.getLogger(LoggerInterceptor.class.getName());

    @Autowired
    private CatalogClient catalogClient;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping(value = "/offers/filter")
    public ResponseEntity<List<OfferDTO>> getFilteredOffers(@RequestBody Filter filter) {
        log.info(filter.toString());
        Map<String, String> map = new HashMap<>();
        map.put("category", filter.getCategory());
        map.put("tagList", filter.getTagList());
        map.put("price", filter.getPrice().toString());
        List<OfferDTO> dtoList = catalogClient.getOffers(map);
        return new ResponseEntity<>(dtoList, HttpStatus.FOUND);
    }

    @PostMapping(value = "/customerEmail/{customerEmail}/orders")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable("customerEmail") String customerEmail) {
        log.info("Create order for " + customerEmail);
        OrderDTO dto = inventoryClient.createOrder(customerEmail);
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }

    @PostMapping(value = "/customerEmail/{customerEmail}/orders/{id}/orderItems")
    public ResponseEntity<OrderDTO> addOrderItem(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id, @RequestBody OfferDTO offerDTO) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setName(offerDTO.getName());
        orderItemDTO.setDescription(offerDTO.getDescription());
        orderItemDTO.setPrice(offerDTO.getPrice());
        log.info("Add "+orderItemDTO.toString()+" for " + customerEmail + "'s order");
        OrderDTO dto = inventoryClient.addOrderItem(customerEmail, id, orderItemDTO);
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders")
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable("customerEmail") String customerEmail) {
        log.info(customerEmail);
        List<OrderDTO> orders = inventoryClient.findCustomerOrders(customerEmail);
        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/paidOrders")
    public ResponseEntity<List<OrderDTO>> getPaidOrders(@PathVariable("customerEmail") String customerEmail) {
        log.info(customerEmail);
        List<OrderDTO> orders = inventoryClient.findPaidOrders(customerEmail);
        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/unpaidOrders")
    public ResponseEntity<List<OrderDTO>> getUnpaidOrders(@PathVariable("customerEmail") String customerEmail) {
        log.info(customerEmail);
        List<OrderDTO> orders = inventoryClient.findUnpaidOrders(customerEmail);
        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/{id}/totalPrice")
    public ResponseEntity<Double> getTotalPrice(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id) {
        log.info(customerEmail);
        Double price = inventoryClient.findTotalPrice(customerEmail, id);
        return new ResponseEntity<>(price, HttpStatus.FOUND);
    }


    @PutMapping(value = "/customerEmail/{customerEmail}/orders/{id}/payment")
    public ResponseEntity<OrderDTO> payForOrders(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id, @RequestBody Double payment) {
        log.info(customerEmail);
        OrderDTO order = inventoryClient.payForOrder(customerEmail, id, payment);
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/status/{status}")
    public ResponseEntity<List<OrderDTO>> findOrdersByStatus(@PathVariable("customerEmail") String customerEmail, @PathVariable("status") String status) {
        log.info(customerEmail);
        List<OrderDTO> orders = inventoryClient.findOrdersByStatus(customerEmail, status);
        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }

    @GetMapping(value = "/customerEmail/{customerEmail}/orders/{id}")
    public ResponseEntity<OrderDTO> findOrdersById(@PathVariable("customerEmail") String customerEmail, @PathVariable("id") Long id) {
        log.info(customerEmail);
        OrderDTO order = inventoryClient.findOrderById(customerEmail, id);
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }


}
