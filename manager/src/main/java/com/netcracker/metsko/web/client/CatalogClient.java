package com.netcracker.metsko.web.client;


import com.netcracker.metsko.entity.OfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CatalogClient {

    protected String serviceUrl="http://localhost:8081";

    private final RestTemplate restTemplate;

    @Autowired
    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    public List<OfferDTO> getOffers(String category, List<String> tagList, double price)
    {
        List<OfferDTO> dtoList = (List<OfferDTO>) restTemplate.getForEntity(serviceUrl + "/offers/categories/{category}/tags/{tags}/price/{price}", OfferDTO.class, category, tagList, price);

        return dtoList;
    }

}
