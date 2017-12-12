package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface OfferDao extends GenericDao<Offer, Long> {


    List<Offer> findByName(String name) throws SQLException;

    Offer findById(Long id) throws SQLException;

    List<Offer> findByTags(List<Tag> tagList) throws SQLException;

    List<Offer> findAvailableOffers() throws SQLException;

    List<Offer> getPriceFromTo(Price priceFrom, Price price) throws SQLException;

    void changePrice(Long offerId, Price price) throws SQLException;

    List<Offer> findAll() throws SQLException;

}
