package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Price;

import java.sql.SQLException;
import java.util.List;

public interface PriceDao extends GenericDao<Price, Long> {

    List<Price> findAll() throws SQLException;
}
