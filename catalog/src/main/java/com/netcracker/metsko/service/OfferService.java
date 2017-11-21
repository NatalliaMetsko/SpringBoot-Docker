package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;

import java.util.List;

public interface OfferService {

    void createOffer(Offer offer);

    Offer updateOffer(Offer offer);

    void deleteOffer(Offer offer);

    void setAvailability(Long id);

    Offer findById(Long id);

    List<Offer> findAll();

    List<Offer> findByTags(List<Tag> tagList);//в параметры мы должны отправлять List<Tag>???

    List<Offer> findAvailableOffers();

    void addPrice(Price price);

    Price changePrice(Price price);

    List<Offer> getPriceFromTo(Price priceFrom, Price priceTo);

    void addTag(Tag tag);

    void addCategory(Category category);

    void removeCategory(Category category);

}
