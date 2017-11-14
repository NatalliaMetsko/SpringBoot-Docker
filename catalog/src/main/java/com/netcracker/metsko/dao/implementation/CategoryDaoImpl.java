package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.CategoryDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {


    @Override
    public List<Category> findAll() throws SQLException {
        return entityManager.createQuery(" FROM Category c").getResultList();
    }

    @Override
    public List<Offer> findOfferByCategory(String categoryName) throws SQLException {
        return null;
    }
}
