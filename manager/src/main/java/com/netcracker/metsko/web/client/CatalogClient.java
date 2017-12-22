package com.netcracker.metsko.web.client;


import com.netcracker.metsko.entity.OfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class CatalogClient {

    private static final Logger LOGGER = Logger.getLogger(CatalogClient.class.getName());

    protected String url = "http://yumasday:8081/api/v1/catalog/offers";

//    @Value("url.catalog")
//    private static String url;

    @Autowired
    private RestTemplate restTemplate;

    public CatalogClient() {
    }

    public List<OfferDTO> getOffers(Map<String, String> offerFilter) {
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(offerFilter);
        ResponseEntity<List<OfferDTO>> response = restTemplate.exchange(url + "/categories/offers/filteredOffers", HttpMethod.POST, entity, new ParameterizedTypeReference<List<OfferDTO>>() {});
        List<OfferDTO> dtoList = response.getBody();

        LOGGER.info(dtoList.toString());

        return dtoList;
    }

}
