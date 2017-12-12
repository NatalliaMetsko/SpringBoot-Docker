package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao extends GenericDao<Category, Long> {

    List<Category> findAll() throws SQLException;

    Category findByName(String categoryName) throws SQLException;

    List<Offer> findOfferList(Long categoryId) throws SQLException;
}
