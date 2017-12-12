package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.PriceDao;
import com.netcracker.metsko.entity.Price;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class PriceDaoImpl extends GenericDaoImpl<Price, Long> implements PriceDao {

    public PriceDaoImpl() {

    }

    public List<Price> findAll() throws SQLException {
        return entityManager.createQuery(" FROM Price p").getResultList();
    }

}
