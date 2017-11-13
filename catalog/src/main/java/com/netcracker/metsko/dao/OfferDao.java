package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.SQLException;
import java.util.List;

public interface OfferDao extends GenericDao<Offer, ID>{

    Category findCategory(ID id) throws SQLException;

    List<Price> findPrice(ID id) throws SQLException;

    List<Tag> findTag(ID id) throws SQLException;

    List<Offer> findByName(String name) throws SQLException;
}
