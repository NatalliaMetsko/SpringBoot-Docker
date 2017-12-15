package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.*;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public interface OfferService {

    void createOffer(Offer offer) throws SQLException, NotCreatedException;

    Offer updateOffer(Offer offer) throws SQLException, NotUpdatedException;

    void deleteOffer(Long id) throws SQLException, NotDeletedException;

    void setAvailability(Long id, boolean availability) throws SQLException, NotUpdatedException;

    Offer findById(Long id) throws SQLException, NotFoundException;

    List<Offer> findAll() throws SQLException, NotFoundException;

    List<Offer> findByTags(List<Tag> tagList) throws SQLException, NotFoundException;

    List<Offer> findAvailableOffers() throws SQLException, NotFoundException;

    void addPrice(Long offerId, Price price) throws SQLException, NotUpdatedException;

    void changePrice(Long offerId, Price price) throws SQLException, NotUpdatedException;

    List<Offer> getPriceFromTo(Price priceFrom, Price priceTo) throws SQLException, NotFoundException;

    void addTag(Long offerId, Tag tag) throws SQLException, NotUpdatedException;

    void removeTag(Long offerId, Tag tag) throws SQLException, NotUpdatedException;

    void addCategory(Long offerId, Category category) throws SQLException, NotUpdatedException;

    void removeCategory(Long offerId) throws SQLException, NotUpdatedException;

    List<Offer> findFilteredOffers(OfferFilter offerFilter) throws SQLException, NotFoundException;
}
