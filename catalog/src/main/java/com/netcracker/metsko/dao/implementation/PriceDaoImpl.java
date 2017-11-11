package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.PriceDao;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;

import java.sql.SQLException;
import java.util.List;

public class PriceDaoImpl extends GenericDaoImpl<Price, Long> implements PriceDao {
    @Override
    public List<Price> findByOffer(Offer offer) throws SQLException {
        return null;
    }
}
