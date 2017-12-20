package com.netcracker.metsko.web.client;

import com.netcracker.metsko.entity.OrderDTO;
import com.netcracker.metsko.entity.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("url.inventory")
    private String url;


    @Autowired
    private RestTemplate restTemplate;

    public InventoryClient() {
    }

    public void createOrder(OrderDTO order) throws RestClientException {
        HttpEntity<OrderDTO> entity = new HttpEntity<OrderDTO>(order);
        restTemplate.postForEntity(url + "/customerEmail/{customerEmail}", entity, OrderDTO.class);
    }


    public OrderDTO addOrderItem(String customerEmail, Long id, OrderItemDTO orderItem) {
        HttpEntity<OrderItemDTO> entity = new HttpEntity<OrderItemDTO>(orderItem);
        ResponseEntity<OrderDTO> response = restTemplate.exchange(url + "/customerEmail/{customerEmail}/orders/{id}/addItems", HttpMethod.PUT, entity, OrderDTO.class, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }


    public List<OrderDTO> findCustomerOrders(String customerEmail) throws RestClientException {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(url + "/customerEmail/{customerEmail}/orders", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }


    public List<OrderDTO> findPaidOrders(String customerEmail) {

        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(url + "/customerEmail/{customerEmail}/orders/paidOrders", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }


    public List<OrderDTO> findUnpaidOrders(String customerEmail) {

        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(url + "/customerEmail/{customerEmail}/orders/unpaidOrders", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }


    public Double findTotalPrice(String customerEmail) {

        ResponseEntity<Double> response = restTemplate.exchange(url + "/customerEmail/{customerEmail}/orders/{id}/totalprice", HttpMethod.GET, null, Double.class, customerEmail);
        Double price = response.getBody();
        return price;
    }

    public OrderDTO payForOrder(String customerEmail, Long id, Double sumToPay) {
        HttpEntity<Double> entity = new HttpEntity<Double>(sumToPay);
        ResponseEntity<OrderDTO> response = restTemplate.exchange(url + "/customerEmail/{customerEmail}/orders/{id}", HttpMethod.PUT, entity, OrderDTO.class, customerEmail, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }

    public List<OrderDTO> findOrdersByStatus(String customerEmail, String status) {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(url + "/customerEmail/{customerEmail}/orders/status/{status}", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
        }, customerEmail, status);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }

    public OrderDTO findOrderById(String customerEmail, Long id) {
        ResponseEntity<OrderDTO> response = restTemplate.exchange(url + "/{id}", HttpMethod.GET, null, OrderDTO.class, customerEmail, id);
        OrderDTO orderDTO = response.getBody();
        return orderDTO;
    }


}
