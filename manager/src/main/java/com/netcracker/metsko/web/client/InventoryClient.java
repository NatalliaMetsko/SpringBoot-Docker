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

    protected String serviceUrl = "http://localhost:8082/api/v1/inventory/customerEmail";//переделать под "http://localhost:8082/api/v1/inventory/customerEmail/", a value = "/{customerEmail}/orders"


    private final RestTemplate restTemplate;

    @Autowired
    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void createOrder(OrderDTO order) throws RestClientException {
        HttpEntity<OrderDTO> entity = new HttpEntity<OrderDTO>(order);
        restTemplate.postForEntity(serviceUrl + "/{customerEmail}/orders", entity, OrderDTO.class);
    }


    public OrderDTO addOrderItem(Long id, OrderItem orderItem) {
        HttpEntity<OrderItem> entity = new HttpEntity<OrderItem>(orderItem);
        ResponseEntity<OrderDTO> response = restTemplate.exchange(serviceUrl + "/{customerEmail}/orders/{id}", HttpMethod.PUT, entity, OrderDTO.class, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }


    public List<OrderDTO> findAllCustomersOrders(String customerEmail) throws RestClientException {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl + "/{customerEmail}/orders", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }

    public List<OrderDTO> findPaidOrders(String customerEmail) {

        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl + "/{customerEmail}/orders/paid", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }

    public List<OrderDTO> findUnpaidOrders(String customerEmail) {

        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl + "/{customerEmail}/orders/unpaid", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }

    public Double findTotalPrice(String customerEmail) {

        ResponseEntity<Double> response = restTemplate.exchange(serviceUrl + "/{customerEmail}/orders/totalprice", HttpMethod.GET, null, Double.class, customerEmail);
        Double price = response.getBody();
        return price;
    }

    public OrderDTO payForOrder(String customerEmail, Long id, Double sumToPay) {
        HttpEntity<Double> entity = new HttpEntity<Double>(sumToPay);
        ResponseEntity<OrderDTO> response = restTemplate.exchange(serviceUrl + "/{customerEmail}/orders/{id}", HttpMethod.PUT, entity, OrderDTO.class, customerEmail, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }

    List<OrderDTO> findOrdersByStatus(String customerEmail, String status) {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl + "/{customerEmail}/orders/status/{status}", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail, status);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }

    OrderDTO findOrderById(String customerEmail, Long id) {
        ResponseEntity<OrderDTO> response = restTemplate.exchange(serviceUrl + "/{customerEmail}/orders/{id}", HttpMethod.GET, null, OrderDTO.class, customerEmail, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }


}
