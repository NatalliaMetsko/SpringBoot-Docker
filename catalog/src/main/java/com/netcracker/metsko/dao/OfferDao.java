package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;

import java.util.List;

public interface OfferDao extends GenericDao<Offer, Long>{

    Category findCategory(Long id);

    List<Price> findPrice(Long id);

    List<Tag> findTag(Long id);

    List<Offer> findByName(String name);
}
