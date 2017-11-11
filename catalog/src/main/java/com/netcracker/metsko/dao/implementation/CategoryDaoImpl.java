package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.CategoryDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {


    @Override
    public List<Category> findAll() throws SQLException {
        return entityManager.createQuery("SELECT * FROM category").getResultList();
    }

    @Override
    public List<Offer> findOfferByCategory(Category category) throws SQLException {
        return null;
    }
}
