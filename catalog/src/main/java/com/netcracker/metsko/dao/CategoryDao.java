package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;

import java.util.List;

public interface CategoryDao extends GenericDao<Category, Long> {

    List<Category> findAll();

    List<Offer> findOfferByCategory(Category category);

}
