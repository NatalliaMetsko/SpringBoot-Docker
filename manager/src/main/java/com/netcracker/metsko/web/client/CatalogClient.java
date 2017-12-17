package com.netcracker.metsko.web.client;


import com.netcracker.metsko.entity.OfferDTO;
import com.netcracker.metsko.entity.OfferFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Component
public class CatalogClient {

    private static final Logger LOGGER = Logger.getLogger(CatalogClient.class.getName());

    protected String serviceUrl = "http://localhost:8081/api/v1/catalog/offers";


    @Value("url.inventory")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public CatalogClient() {
    }

    public List<OfferDTO> getOffers(OfferFilter offerFilter) {
        HttpEntity<OfferFilter> entity = new HttpEntity<OfferFilter>(offerFilter);
        ResponseEntity<List<OfferDTO>> response = restTemplate.exchange(serviceUrl + "/categories/offers/filteredOffers", HttpMethod.GET, entity, new ParameterizedTypeReference<List<OfferDTO>>() {});
        List<OfferDTO> dtoList = response.getBody();

        LOGGER.info(dtoList.toString());

        return dtoList;
    }

}
