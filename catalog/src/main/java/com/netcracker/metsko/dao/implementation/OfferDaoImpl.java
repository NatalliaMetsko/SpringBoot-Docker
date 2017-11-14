package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.OfferDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public class OfferDaoImpl extends GenericDaoImpl<Offer, Long> implements OfferDao {

    public List<Offer> findAll() throws SQLException{
        return entityManager.createQuery(" FROM Offer o").getResultList();
    }

    @Override
    public Category findCategory(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Price> findPrice(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Tag> findTag(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Offer> findByName(String name) throws SQLException {
        return null;
    }
}
