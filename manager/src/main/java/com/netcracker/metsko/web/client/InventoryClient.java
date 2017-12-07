package com.netcracker.metsko.web.client;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Order;
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

    protected String serviceUrl="http://localhost:8082/api/v1/inventory/orders";


    private final RestTemplate restTemplate;

    @Autowired
    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void createOrder(OrderDTO order) throws RestClientException
    {
        HttpEntity<OrderDTO> entity = new HttpEntity<OrderDTO>(order);
        restTemplate.postForEntity(serviceUrl, entity, OrderDTO.class);
    }


    public OrderDTO addOrderItem(Long id, OrderItem orderItem)
    {
        HttpEntity<OrderItem> entity = new HttpEntity<OrderItem>(orderItem);
        ResponseEntity<OrderDTO> response = restTemplate.exchange(serviceUrl+"/{id}", HttpMethod.PUT, entity, OrderDTO.class, id);
        OrderDTO orderDTO = response.getBody();
        return  orderDTO;
    }


    public List<OrderDTO> findAllCustomersOrders(String customerEmail) throws RestClientException
    {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(serviceUrl+"/customerEmail/{customerEmail}", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {}, customerEmail);
        List<OrderDTO> dtoList = response.getBody();
        return dtoList;
    }




}
