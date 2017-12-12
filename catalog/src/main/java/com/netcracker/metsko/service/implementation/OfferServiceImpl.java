package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.CategoryDao;
import com.netcracker.metsko.dao.OfferDao;
import com.netcracker.metsko.dao.PriceDao;
import com.netcracker.metsko.dao.TagDao;
import com.netcracker.metsko.entity.*;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
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

    @Autowired
    private PriceDao priceDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private TagDao tagDao;


    @Transactional
    public void createOffer(Offer offer) throws NotCreatedException, SQLException {
        try {
            offerDao.create(offer);
        } catch (Exception e) {
            throw new NotCreatedException("The offer" + ExceptionMessage.NOT_CREATED + " " + ExceptionMessage.NOT_NULL_ENTITY);
        }
    }

    @Transactional
    public Offer updateOffer(Offer offer) throws NotUpdatedException, SQLException {
        Offer updatedOffer = (Offer) offerDao.update(offer);
        if (updatedOffer != null) {
            return updatedOffer;
        } else {
            throw new NotUpdatedException("The offer" + ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public void deleteOffer(Long offerId) throws NotDeletedException, SQLException {
        try {
            offerDao.delete(offerId);
        } catch (Exception e) {
            throw new NotDeletedException("The offer" + ExceptionMessage.NOT_DELETED);
        }
    }


    @Transactional
    public void setAvailability(Long id, boolean availability) throws NotUpdatedException, SQLException {
        Offer offer = (Offer) offerDao.read(id);
        if (offer != null) {
            offer.setAvailability(availability);
            offerDao.update(offer);
        } else {
            throw new NotUpdatedException("The offer" + ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public Offer findById(Long id) throws NotFoundException, SQLException {
        Offer foundOffer = offerDao.findById(id);
        if (foundOffer != null) {
            return foundOffer;
        } else {
            throw new NotFoundException("The offer" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public List<Offer> findAll() throws NotFoundException, SQLException {
        List<Offer> offerList = offerDao.findAll();
        if (offerList != null) {
            return offerList;
        } else {
            throw new NotFoundException("The offer" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public List<Offer> findByTags(List<Tag> tagList) throws NotFoundException, SQLException {
        List<Offer> offerList = offerDao.findByTags(tagList);
        if (offerList != null) {
            return offerList;
        } else {
            throw new NotFoundException("The offers" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public List<Offer> findAvailableOffers() throws NotFoundException, SQLException {
        List<Offer> offerList = offerDao.findAvailableOffers();
        if (offerList != null) {
            return offerList;
        } else {
            throw new NotFoundException("Available offers" + ExceptionMessage.NOT_FOUND);
        }
    }


    @Transactional
    public void addPrice(Long offerId, Price price) throws NotUpdatedException, SQLException {
        Offer offer = (Offer) offerDao.read(offerId);
        if (offer != null) {
            priceDao.create(price);
            price.setOffer(offer);
            offer.setPrice(price);
            offerDao.update(offer);
            priceDao.update(price);
        } else {
            throw new NotUpdatedException("The offer's price" + ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public void changePrice(Long offerId, Price price) throws NotUpdatedException, SQLException {
        try {
            priceDao.create(price);
            offerDao.changePrice(offerId, price);
        } catch (Exception e) {
            throw new NotUpdatedException("The offer's price" + ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public List<Offer> getPriceFromTo(Price priceFrom, Price priceTo) throws NotFoundException, SQLException {
        List<Offer> offerList = offerDao.getPriceFromTo(priceFrom, priceTo);
        if (offerList != null) {
            return offerList;
        } else {
            throw new NotFoundException("The offers" + ExceptionMessage.NOT_FOUND);
        }
    }


    @Transactional
    public void addTag(Long id, Tag tag) throws NotUpdatedException, SQLException {
        Offer offer = (Offer) offerDao.read(id);
        if (offer != null) {
            offer.addTag(tag);
            offerDao.update(offer);
            tagDao.update(tag);
        } else {
            throw new NotUpdatedException("The tag" + ExceptionMessage.NOT_ADDED);
        }
    }

    @Override
    public void removeTag(Long offerId, Tag tag) throws NotUpdatedException, SQLException {
        Offer offer = (Offer) offerDao.read(offerId);
        if (offer != null) {
            if (offer.getTagList().contains(tag)) {
                offer.removeTag(tag);
                tagDao.update(tag);
                offerDao.update(offer);
            } else {
                throw new NotUpdatedException("The tag doesn't exist for this offer");
            }

        } else {
            throw new NotUpdatedException("The offer" + ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public void addCategory(Long offerId, Category category) throws NotUpdatedException, SQLException {
        Offer offer = (Offer) offerDao.read(offerId);
        if (offer != null) {
            category.addOffer(offer);
            categoryDao.update(category);
            offerDao.update(offer);
        } else {
            throw new NotUpdatedException("The category " + ExceptionMessage.NOT_ADDED);
        }
    }

    @Transactional
    public void removeCategory(Long idOffer) throws NotUpdatedException, SQLException {
        Offer offer = (Offer) offerDao.read(idOffer);
        if (offer != null) {
            Category category = offer.getCategory();
            category.removeOffer(offer);
            offer.setCategory(null);
            offerDao.update(offer);
            categoryDao.update(category);
        } else {
            throw new NotUpdatedException("The category " + ExceptionMessage.NOT_DELETED);
        }
    }
}
