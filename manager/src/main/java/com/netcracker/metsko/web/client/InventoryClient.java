package com.netcracker.metsko.web.client;

import com.netcracker.metsko.entity.OrderDTO;
import com.netcracker.metsko.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InventoryClient {

    protected String serviceUrl = "http://localhost:8082/api/v1/inventory/orders";//переделать под "http://localhost:8082/api/v1/inventory/customerEmail/", a value = "/{customerEmail}/orders"

    @Autowired
    private RestTemplate restTemplate;

    public InventoryClient() {
    }
//+
    public void createOrder(OrderDTO order) throws RestClientException {
        HttpEntity<OrderDTO> entity = new HttpEntity<OrderDTO>(order);
        restTemplate.postForEntity(serviceUrl + "/customerEmail/{customerEmail}", entity, OrderDTO.class);
    }

//+
    public OrderDTO addOrderItem(String customerEmail, Long id, OrderItem orderItem) {
        HttpEntity<OrderItem> entity = new HttpEntity<OrderItem>(orderItem);
        ResponseEntity<OrderDTO> response = restTemplate.exchange(serviceUrl + "/customerEmail/{customerEmail}/orders/{id}/addItems", HttpMethod.PUT, entity, OrderDTO.class, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }

//+
    public List<OrderDTO> findAllCustomersOrders(String customerEmail) throws RestClientException {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl + "/customerEmail/{customerEmail}/orders", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }
//+
    public List<OrderDTO> findPaidOrders(String customerEmail) {

        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl + "/customerEmail/{customerEmail}/orders/paidOrders", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }
//+
    public List<OrderDTO> findUnpaidOrders(String customerEmail) {

        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl + "/customerEmail/{customerEmail}/orders/unpaidOrders", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }
//+
    public Double findTotalPrice(String customerEmail) {

        ResponseEntity<Double> response = restTemplate.exchange(serviceUrl + "/customerEmail/{customerEmail}/orders/{id}/totalprice", HttpMethod.GET, null, Double.class, customerEmail);
        Double price = response.getBody();
        return price;
    }

    public OrderDTO payForOrder(String customerEmail, Long id, Double sumToPay) {
        HttpEntity<Double> entity = new HttpEntity<Double>(sumToPay);
        ResponseEntity<OrderDTO> response = restTemplate.exchange(serviceUrl + "/customerEmail/{customerEmail}/orders/{id}", HttpMethod.PUT, entity, OrderDTO.class, customerEmail, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }
//+
    List<OrderDTO> findOrdersByStatus(String customerEmail, String status) {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl + "/customerEmail/{customerEmail}/orders/status/{status}", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail, status);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }
//+
    OrderDTO findOrderById(String customerEmail, Long id) {
        ResponseEntity<OrderDTO> response = restTemplate.exchange(serviceUrl + "/{id}", HttpMethod.GET, null, OrderDTO.class, customerEmail, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }


}
