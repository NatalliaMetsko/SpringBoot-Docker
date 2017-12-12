package com.netcracker.metsko.web.client.feignclient;

import com.netcracker.metsko.entity.OfferDTO;
import com.netcracker.metsko.entity.OfferFilter;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient("catalog-service")
public interface CatalogFeignClient {

    @GetMapping(value = "/categories/offers/filtered")
    List<OfferDTO> findFilteredOffers(@RequestBody OfferFilter offerFilter);

}
