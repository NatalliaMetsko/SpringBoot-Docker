package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.TagDao;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class TagDaoImpl extends GenericDaoImpl<Tag, Long> implements TagDao {

    public TagDaoImpl() {
    }

    @Override
    public List<Tag> findAll() throws SQLException{
        return entityManager.createQuery(" select  t from Tag t").getResultList();
    }

    @Override
    public Tag findByName(String tagName) throws SQLException {
        return entityManager.createQuery("select t from Tag t where t.tag='"+tagName+"'", Tag.class).getSingleResult();
    }


}
