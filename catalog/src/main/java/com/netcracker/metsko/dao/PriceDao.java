package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;

import java.util.List;

public interface PriceDao extends GenericDao<Price, Long> {
    List<Price> findByOffer(Offer offer);
}
