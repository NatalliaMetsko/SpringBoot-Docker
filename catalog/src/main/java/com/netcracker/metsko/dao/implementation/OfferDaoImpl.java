package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.OfferDao;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class OfferDaoImpl extends GenericDaoImpl<Offer, Long> implements OfferDao {

    public OfferDaoImpl() {
    }

    public List<Offer> findAll() throws SQLException{
        return entityManager.createQuery("select o from Offer o", Offer.class).getResultList();
    }

    @Override
    public List<Offer> findByName(String name) throws SQLException {
        return entityManager.createQuery("from Offer o where o.name='"+name+"'", Offer.class).getResultList();
    }

    @Override
    public Offer findById(Long id) throws SQLException {
        return (Offer) entityManager.createQuery("select o from Offer o where o.id="+id+"", Offer.class).getSingleResult();
    }

    @Override
    public List<Offer> findByTags(List<Tag> tagList) throws SQLException {
        return entityManager.createQuery("select o from Offer  o where o.tagList="+tagList,Offer.class).getResultList();
    }

    @Override
    public List<Offer> findAvailableOffers() throws SQLException {
        return entityManager.createQuery("select o from Offer  o where o.availability="+true,Offer.class).getResultList();
    }

    @Override
    public List<Offer> getPriceFromTo(Price priceFrom, Price priceTo) throws SQLException {
        return entityManager.createQuery("select o from Offer  o where o.price between "+priceFrom.getPrice()+" and "+priceTo.getPrice()+" ", Offer.class).getResultList();
    }

    @Override
    public void changePrice(Long offerId, Price price) throws SQLException {
        entityManager.createQuery("select o from Offer  o where o.id="+offerId, Offer.class).getSingleResult().setPrice(price);
    }
}
