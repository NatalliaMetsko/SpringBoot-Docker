package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao extends GenericDao<Category, Long> {

    List<Category> findAll()throws SQLException;

    List<Offer> findOfferByCategory(String categoryName)throws SQLException;

}
