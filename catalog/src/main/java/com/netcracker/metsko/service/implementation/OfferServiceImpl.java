package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.OfferDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;


@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferDao offerDao;

    @Transactional
    public void createOffer(Offer offer) throws SQLException {
            offerDao.create(offer);
    }

    @Transactional
    public Offer updateOffer(Offer offer)throws SQLException {
        return (Offer) offerDao.update(offer);
    }

    @Transactional
    public void deleteOffer(Offer offer)throws SQLException {
            offerDao.delete(offer);
    }


    @Transactional
    public void setAvailability(Long id, boolean availability) throws SQLException {
        Offer offer = (Offer) offerDao.read(id);
        offer.setAvailability(availability);
        offerDao.update(offer);
    }

    @Transactional
    public Offer findById(Long id)throws SQLException {
        return offerDao.findById(id);
    }

    @Transactional
    public List<Offer> findAll()throws SQLException {
        return offerDao.findAll();
    }

    @Transactional
    public List<Offer> findByTags(List<Tag> tagList)throws SQLException {
        return offerDao.findByTags(tagList);
    }

    @Transactional
    public List<Offer> findAvailableOffers()throws SQLException {
        return offerDao.findAvailableOffers();
    }


    @Transactional
    public void addPrice(Long offerId, Price price) throws SQLException {
        Offer offer= (Offer) offerDao.read(offerId);
        offer.setPrice(price);
        offerDao.update(offer);
    }

    @Transactional
    public void changePrice(Long offerId, Price price)throws SQLException {
        offerDao.changePrice(offerId, price);
    }

    @Transactional
    public List<Offer> getPriceFromTo(Price priceFrom, Price priceTo) throws SQLException{
        return offerDao.getPriceFromTo(priceFrom, priceTo);
    }


    @Transactional
    public void addTag(Long id, Tag tag) throws SQLException {
        Offer offer = (Offer) offerDao.read(id);
        offer.addTag(tag);
        offerDao.update(offer);
    }

    @Override
    public void removeTag(Long offerId, Tag tag) throws SQLException {
        Offer offer = (Offer) offerDao.read(offerId);
        offer.getTagList().remove(tag);
    }

    @Transactional
    public void addCategory(Long offerId, Category category) throws SQLException {
        Offer offer = (Offer) offerDao.read(offerId);
        offer.setCategory(category);
        offerDao.update(offer);
    }

    @Transactional
    public void removeCategory(Long idOffer) throws SQLException {
        Offer offer = (Offer) offerDao.read(idOffer);
        offer.setCategory(null);
    }
}
