package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Offer;

import java.sql.SQLException;
import java.util.List;

public interface OfferDao extends GenericDao<Offer, Long> {


    List<Offer> findByName(String name) throws SQLException;

    Offer findById(Long id) throws SQLException;

    List<Offer> findOffersByAvailability(boolean availability) throws SQLException;

    List<Offer> getPriceFromTo(Double priceFrom, Double price) throws SQLException;

    List<Offer> getPriceFrom(Double priceFrom) throws SQLException;

    List<Offer> getPriceTo(Double priceTo) throws SQLException;

    List<Offer> findAll() throws SQLException;

}
