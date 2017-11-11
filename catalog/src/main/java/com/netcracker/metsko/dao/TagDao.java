package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;

import java.util.List;

public interface TagDao extends GenericDao<Tag, Long> {

    List<Tag> findAll();

    List<Offer> findOfferByTag(Tag tag);
}
