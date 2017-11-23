package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {

    void createCategory(Category category) throws SQLException;

    Category findById(Long id) throws SQLException;

    Category findByName(String name) throws SQLException;

    List<Category> findAll() throws SQLException;

    Category updateCategory(Category category) throws SQLException;

    void deleteCategory(Category category) throws SQLException;

    List<Offer> findCategoryOffers(Category category) throws SQLException;

}
