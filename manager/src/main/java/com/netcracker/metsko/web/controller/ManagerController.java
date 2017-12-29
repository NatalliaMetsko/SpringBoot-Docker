package com.netcracker.metsko.web.controller;


import com.netcracker.metsko.entity.Filter;
import com.netcracker.metsko.entity.OfferDTO;
import com.netcracker.metsko.entity.OrderDTO;
import com.netcracker.metsko.entity.OrderItemDTO;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.web.client.CatalogClient;
import com.netcracker.metsko.web.client.InventoryClient;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/manager")
@Api(value = "Controller", description = "This is manager controller")
public class ManagerController {

    @Autowired
    private CatalogClient catalogClient;

    @Autowired
    private InventoryClient inventoryClient;

    @PostMapping(value = "/offers/filters")
    public ResponseEntity<List<OfferDTO>> getFilteredOffers(@Validated @RequestBody Filter filter) throws NotFoundException, SQLException {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("category", filter.getCategory());
            map.put("tagList", filter.getTagList());
            if (filter.getMin() == null) {
                map.put("min", Double.toString(0));
            } else {
                map.put("min", Double.toString(filter.getMin()));
            }
            if (filter.getMax() == null) {
                map.put("max", Double.toString(0));
            } else {
                map.put("max", Double.toString(filter.getMax()));
            }
            List<OfferDTO> dtoList = catalogClient.getOffers(map);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<OrderDTO> createOrder(@Email @RequestBody String customerEmail) throws SQLException, NotCreatedException {
        try {
            OrderDTO dto = inventoryClient.createOrder(customerEmail);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotCreatedException();
        }
    }

    @PostMapping(value = "/orders/{id}/orderItems")
    public ResponseEntity<OrderDTO> addOrderItem(@PathVariable("id") Long id, @RequestBody Long offerId) throws NotFoundException, SQLException, NotUpdatedException {
        try {
            OfferDTO offerDTO = catalogClient.findOfferById(offerId);
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setName(offerDTO.getName());
            orderItemDTO.setDescription(offerDTO.getDescription());
            orderItemDTO.setPrice(offerDTO.getPrice());
            OrderDTO dto = inventoryClient.addOrderItem(id, orderItemDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotUpdatedException();
        }
    }

    @DeleteMapping(value = "/orders/{id}/orderItems")
    public ResponseEntity<OrderDTO> removeOrderItem(@PathVariable("id") Long id, @RequestBody Long orderItemId) throws NotUpdatedException {
        try {
            OrderDTO orderDTO = inventoryClient.removeOrderItem(id, orderItemId);
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotUpdatedException();
        }
    }

    @GetMapping(value = "/orders/payments")
    public ResponseEntity<List<OrderDTO>> getOrdersByPayment(@RequestParam("signPayment") boolean signPayment, Model model) throws NotFoundException {
        try {
            List<OrderDTO> orders = inventoryClient.getOrdersByPayment(signPayment);
            model.addAttribute("orders", orders);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GetMapping(value = "/orders/{id}/totalprices")
    public ResponseEntity<Double> getTotalPrice(@PathVariable("id") Long id) throws NotFoundException {
        try {
            Double price = inventoryClient.findTotalPrice(id);
            return new ResponseEntity<>(price, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @PutMapping(value = "/orders/{id}/payments")
    public ResponseEntity<OrderDTO> payForOrders(@PathVariable("id") Long id) throws NotUpdatedException, SQLException {
        try {
            OrderDTO order = inventoryClient.payForOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotUpdatedException();
        }
    }

    @GetMapping(value = "/orders/status/{status}")
    public ResponseEntity<List<OrderDTO>> findOrdersByStatus(@PathVariable("status") String status) throws NotFoundException, SQLException {
        try {
            List<OrderDTO> orders = inventoryClient.findOrdersByStatus(status);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<OrderDTO> findOrdersById(@PathVariable("id") Long id) throws NotFoundException, SQLException {
        try {
            OrderDTO order = inventoryClient.findOrderById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

//    @GetMapping("/home")
//    public String home(Model map) {
//        map.addAttribute("name", "Manager");
//        return "home";
//    }
}
