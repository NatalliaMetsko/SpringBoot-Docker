package com.netcracker.metsko.web.client.feignclient;

import com.netcracker.metsko.entity.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient("catalog-service")
public interface CatalogFeignClient {

    @GetMapping(value = "/offers/categories/{category}/tags/{tags}/price/{price}")
    List<OfferDTO> findFilteredOffers(@PathVariable("category") String category,
                                      @PathVariable("tags") List<String> tagList,
                                      @PathVariable("price") double price);

}
