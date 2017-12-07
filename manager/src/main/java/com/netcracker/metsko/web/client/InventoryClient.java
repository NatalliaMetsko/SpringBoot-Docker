package com.netcracker.metsko.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    protected String serviceUrl="http://localhost:8082api/v1/inventory/orders";


    private final RestTemplate restTemplate;

    @Autowired
    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



}
