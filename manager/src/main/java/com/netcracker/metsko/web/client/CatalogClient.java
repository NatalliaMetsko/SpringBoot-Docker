package com.netcracker.metsko.web.client;


import com.netcracker.metsko.entity.OfferDTO;
import com.netcracker.metsko.entity.OfferFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CatalogClient {

    protected String serviceUrl = "http://localhost:8081/api/v1/catalog/offers";

    private final RestTemplate restTemplate;

    @Autowired
    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<OfferDTO> getOffers(OfferFilter offerFilter) {
        HttpEntity<OfferFilter> entity = new HttpEntity<OfferFilter>(offerFilter);
        ResponseEntity<List<OfferDTO>> response = restTemplate.exchange(serviceUrl + "/categories/offers/filtered", HttpMethod.GET, entity, new ParameterizedTypeReference<List<OfferDTO>>() {});
        List<OfferDTO> dtoList = response.getBody();
        return dtoList;
    }

}
