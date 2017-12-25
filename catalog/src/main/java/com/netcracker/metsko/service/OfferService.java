package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Service
public interface OfferService {

    void createOffer(Offer offer) throws SQLException, NotCreatedException;

    Offer updateOffer(Offer offer) throws SQLException, NotUpdatedException;

    void deleteOffer(Long id) throws SQLException, NotDeletedException;

    void setAvailability(Long id, boolean availability) throws SQLException, NotUpdatedException;

    Offer findById(Long id) throws SQLException, NotFoundException;

    List<Offer> findAll() throws SQLException, NotFoundException;

    List<Offer> findByTags(String tagList) throws SQLException, NotFoundException;

    List<Offer> findOffersByAvailability(boolean availability) throws SQLException, NotFoundException;

    void addPrice(Long offerId, Price price) throws SQLException, NotUpdatedException;

    void changePrice(Long offerId, Double price) throws SQLException, NotUpdatedException;

    List<Offer> getPriceFromTo(Double priceFrom, Double priceTo) throws SQLException, NotFoundException;

    void addTag(Long offerId, Long tagId) throws SQLException, NotUpdatedException;

    void removeTag(Long offerId, Long tagId) throws SQLException, NotUpdatedException;

    void addCategory(Long offerId, Long categoryId) throws SQLException, NotUpdatedException;

    void removeCategory(Long offerId) throws SQLException, NotUpdatedException;

    List<Offer> findFilteredOffers(Map<String, String> filter) throws SQLException, NotFoundException;
}
