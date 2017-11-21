package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.OfferDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.service.OfferService;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class OfferServiceImpl implements OfferService {

    private final OfferDaoImpl offerDao;

    @Autowired
    public OfferDaoImpl getOfferDaoImpl()
    {
        return new OfferDaoImpl();
    }


    @Transactional
    public void createOffer(Offer offer) {
            offerDao.create(offer);
    }

    @Transactional
    public Offer updateOffer(Offer offer) {
        return offerDao.update(offer);
    }

    @Transactional
    public void deleteOffer(Offer offer) {
            offerDao.delete(offer);
    }

    @Transactional
    public void setAvailability(Long id, boolean availability) {
        Offer offer = offerDao.read(id);
        offer.setAvailability(availability);
        offerDao.update(offer);
    }

    @Transactional
    public Offer findById(Long id) {
        return offerDao.findById(id);
    }

    @Transactional
    public List<Offer> findAll() {
        return offerDao.findAll();
    }

    @Transactional
    public List<Offer> findByTags(List<Tag> tagList) {
        return offerDao.findByTags(tagList);
    }

    @Transactional
    public List<Offer> findAvailableOffers() {
        return offerDao.findAvailableOffers();
    }

    @Transactional
    public void addPrice(Long id, Price price) {
        offerDao.read(id);
        offer.addPrice(price);
        offerDao.update(offer);
    }

    @Transactional
    public Price changePrice(Price price) { //уточнить
        return offerDao.changePrice(price);
    }

    @Transactional
    public List<Offer> getPriceFromTo(Price priceFrom, Price priceTo) {
        return offerDao.getPriceFromTo(priceFrom, priceTo);
    }

    @Transactional
    public void addTag(Long id, Tag tag) {
        Offer offer =offerDao.read(id);
        offer.addTag(tag);
        offerDao.update(offer);
    }

    @Transactional
    public void addCategory(Long id, Category category) {
        Offer offer = offerDao.read(id);
        offer.setCategory(category);
        offerDao.update(offer);
    }

    @Transactional
    public void removeCategory(Long id) {
        Offer offer = offerDao.read(id);
        offer.setCategory(null);
    }
}
