package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagDao extends GenericDao<Tag, Long> {

    List<Tag> findAll() throws SQLException;

    Tag findByName(String tagName) throws SQLException;


}
