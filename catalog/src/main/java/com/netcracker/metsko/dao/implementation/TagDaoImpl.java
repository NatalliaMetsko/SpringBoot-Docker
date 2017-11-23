package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.TagDao;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public class TagDaoImpl extends GenericDaoImpl<Tag, Long> implements TagDao {

    @Override
    public List<Tag> findAll() throws SQLException{
        return entityManager.createQuery(" FROM Tag t").getResultList();
    }


}
