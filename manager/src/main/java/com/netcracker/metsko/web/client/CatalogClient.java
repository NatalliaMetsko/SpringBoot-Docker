package com.netcracker.metsko.web.client;


import com.netcracker.metsko.entity.OfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CatalogClient {

    protected String serviceUrl="http://localhost:8081/api/v1/catalog/offers";

    private final RestTemplate restTemplate;

    @Autowired
    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<OfferDTO> getOffers(String category, List<String> tagList, double price)
    {
        ResponseEntity<List<OfferDTO>> response = restTemplate.exchange(serviceUrl+"/categories/{category}/tags/{tags}/price/{price}", HttpMethod.GET, null, new ParameterizedTypeReference<List<OfferDTO>>() {}, category, tagList, price);
        List<OfferDTO> dtoList = response.getBody();
        return dtoList;
    }

}
