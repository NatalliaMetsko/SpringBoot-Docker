package com.netcracker.metsko.web.client;


import com.netcracker.metsko.entity.OfferDTO;
import com.netcracker.metsko.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class CatalogClient {

    @Value("${url.catalog}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public CatalogClient() {
    }

    public List<OfferDTO> getOffers(Map<String, String> offerFilter) throws SQLException, NotFoundException{
        try {
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(offerFilter);
            ResponseEntity<List<OfferDTO>> response = restTemplate.exchange(url + "/categories/offers/filteredOffers", HttpMethod.POST, entity, new ParameterizedTypeReference<List<OfferDTO>>() {
            });
            List<OfferDTO> dtoList = response.getBody();
            return dtoList;
        }catch (Exception e)
        {
            throw new NotFoundException();
        }
    }

    public OfferDTO findOfferById(Long offerId) throws SQLException, NotFoundException{
        try {
            ResponseEntity<OfferDTO> response = restTemplate.exchange(url + "/{id}",HttpMethod.GET,null, OfferDTO.class, offerId);
            return response.getBody();
        }catch (Exception e)
        {
            throw new NotFoundException();
        }
    }

}
