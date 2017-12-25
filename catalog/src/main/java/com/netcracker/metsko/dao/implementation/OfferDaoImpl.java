package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.OfferDao;
import com.netcracker.metsko.entity.Offer;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class OfferDaoImpl extends GenericDaoImpl<Offer, Long> implements OfferDao {

    public OfferDaoImpl() {
    }

    public List<Offer> findAll() throws SQLException {
        return entityManager.createQuery("select o from Offer o", Offer.class).getResultList();
    }

    @Override
    public List<Offer> findByName(String name) throws SQLException {
        return entityManager.createQuery("from Offer o where o.name='" + name + "'", Offer.class).getResultList();
    }

    @Override
    public Offer findById(Long id) throws SQLException {
        return (Offer) entityManager.createQuery("select o from Offer o where o.id=" + id + "", Offer.class).getSingleResult();
    }

    @Override
    public List<Offer> findOffersByAvailability(boolean availability) throws SQLException {
        return entityManager.createQuery("select o from Offer  o where o.availability=" + availability, Offer.class).getResultList();
    }

    @Override
    public List<Offer> getPriceFromTo(Double priceFrom, Double priceTo) throws SQLException {
        return entityManager.createQuery("select o from Offer  o where o.availability="+true+" and o.price between " + priceFrom + " and " + priceTo, Offer.class).getResultList();
    }

    @Override
    public List<Offer> getPriceFrom(Double priceFrom) throws SQLException {
        return entityManager.createQuery("select o from Offer  o where o.price > " + priceFrom, Offer.class).getResultList();
    }

    @Override
    public List<Offer> getPriceTo(Double priceTo) throws SQLException {
        return entityManager.createQuery("select o from Offer  o where o.price < " + priceTo, Offer.class).getResultList();
    }

}
