package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.SQLException;
import java.util.List;

public interface TagDao extends GenericDao<Tag, Long> {

    List findAll() throws SQLException;

    List<Offer> findOfferByTag(Tag tag)throws SQLException;
}
