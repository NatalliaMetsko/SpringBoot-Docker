package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;

import java.util.List;

public interface CategoryService {

    Category createCategory();

    Category findById(Long id);

    Category findByName(String name);

    Category findAll();

    Category updateCategory(Category category);

    void deleteCategory(Category category);

    List<Offer> findCategoryOffers(Category category);

}
