package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.CategoryDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {


    @Override
    public List<Category> findAll() throws SQLException {
        return entityManager.createQuery("select c from Category c",Category.class).getResultList();
    }

    @Override
    public Category findByName(String categoryName) throws SQLException {
        return entityManager.createQuery("FROM Category c where c.name ='"+categoryName+"' ", Category.class).getSingleResult();
    }

    @Override
    public List<Offer> findOfferList(Category category) throws SQLException {
        return category.getOfferList();
    }

}
