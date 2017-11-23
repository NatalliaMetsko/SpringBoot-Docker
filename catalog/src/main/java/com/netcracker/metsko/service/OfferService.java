package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface OfferService {

    void createOffer(Offer offer) throws SQLException;

    Offer updateOffer(Offer offer) throws SQLException;

    void deleteOffer(Long id) throws SQLException;

    void setAvailability(Long id, boolean availability) throws SQLException;

    Offer findById(Long id) throws SQLException;

    List<Offer> findAll() throws SQLException;

    List<Offer> findByTags(List<Tag> tagList) throws SQLException;//в параметры мы должны отправлять List<Tag>???

    List<Offer> findAvailableOffers() throws SQLException;

    void addPrice(Long offerId, Price price) throws SQLException;

    void changePrice(Long offerId, Price price) throws SQLException;

    List<Offer> getPriceFromTo(Price priceFrom, Price priceTo) throws SQLException;

    void addTag(Long offerId, Tag tag) throws SQLException;

    void removeTag(Long offerId, Tag tag) throws SQLException;

    void addCategory(Long offerId, Category category) throws SQLException;

    void removeCategory(Long offerId) throws SQLException;
}
