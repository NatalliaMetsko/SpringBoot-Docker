package com.netcracker.metsko.web.client;

import com.netcracker.metsko.entity.OrderDTO;
import com.netcracker.metsko.entity.OrderItemDTO;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

@Component
public class InventoryClient {

    @Value("${url.inventory}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public InventoryClient() {
    }

    public OrderDTO createOrder(String customerEmail) throws RestClientException, NotCreatedException, SQLException {
        try {
            HttpEntity<String> entity = new HttpEntity<>(customerEmail);
            ResponseEntity<OrderDTO> response = restTemplate.postForEntity(url + "/orders", entity, OrderDTO.class);
            OrderDTO orderDTO = response.getBody();
            return orderDTO;
        } catch (Exception e) {
            throw new NotCreatedException();
        }
    }

    public OrderDTO addOrderItem(Long id, OrderItemDTO orderItem) throws SQLException, NotUpdatedException {
        try {
            HttpEntity<OrderItemDTO> entity = new HttpEntity<>(orderItem);
            ResponseEntity<OrderDTO> response = restTemplate.exchange(url + "/orders/{id}/orderitems", HttpMethod.POST, entity, OrderDTO.class, id);
            OrderDTO orderDTO = response.getBody();
            return orderDTO;
        } catch (Exception e) {
            throw new NotUpdatedException();
        }
    }

    public OrderDTO removeOrderItem(Long id, Long orderItemId) throws SQLException, NotUpdatedException {
        try {
            HttpEntity<Long> entity = new HttpEntity<>(orderItemId);
            ResponseEntity<OrderDTO> response = restTemplate.exchange(url + "/orders/{id}/orderitems", HttpMethod.DELETE, entity, OrderDTO.class, id);
            OrderDTO orderDTO = response.getBody();
            return orderDTO;
        } catch (Exception e) {
            throw new NotUpdatedException();
        }
    }

    public List<OrderDTO> getOrdersByPayment(boolean signPayment) throws SQLException, NotFoundException {
        try {
            ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(url + "/orders/payments?signPayment={signPayment}", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
            }, signPayment);
            List<OrderDTO> dtoList = response.getBody();
            return dtoList;
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    public Double findTotalPrice(Long id) throws SQLException, NotFoundException {
        try {
            ResponseEntity<Double> response = restTemplate.exchange(url + "/orders/{id}/totalprices", HttpMethod.GET, null, Double.class, id);
            Double price = response.getBody();
            return price;
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    public OrderDTO payForOrder(Long id) throws SQLException, NotUpdatedException {
        try {
            ResponseEntity<OrderDTO> response = restTemplate.exchange(url + "/orders/{id}/payments", HttpMethod.PUT, null, OrderDTO.class, id);
            OrderDTO orderDTO = response.getBody();
            return orderDTO;
        }catch (Exception e){
            throw new NotUpdatedException();
        }
    }

    public List<OrderDTO> findOrdersByStatus(String status) throws SQLException, NotFoundException {
        try {
            ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(url + "/orders/status/{status}", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
            }, status);
            List<OrderDTO> dtoList = response.getBody();
            return dtoList;
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    public OrderDTO findOrderById(Long id) throws SQLException, NotFoundException {
        try {
            ResponseEntity<OrderDTO> response = restTemplate.exchange(url + "/orders/{id}", HttpMethod.GET, null, OrderDTO.class, id);
            OrderDTO orderDTO = response.getBody();
            return orderDTO;
        } catch (Exception e) {
            throw new NotFoundException();
        }

    }
}
