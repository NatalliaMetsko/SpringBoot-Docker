package com.netcracker.metsko.web.client.feignclient;

import com.netcracker.metsko.entity.OfferDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;


@FeignClient("catalog-service")
public interface CatalogFeignClient {

    @PostMapping(value = "/categories/offers/filteredOffers")
    List<OfferDTO> findFilteredOffers(@RequestBody Map<String, String> offerFilter);

}
