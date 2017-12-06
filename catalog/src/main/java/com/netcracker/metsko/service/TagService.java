package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.sql.SQLException;
import java.util.List;

@Service
public interface TagService {

    void createTag(Tag tag) throws SQLException;

    Tag findTagById(Long id) throws SQLException;

    Tag findTagByName(String tagName) throws SQLException;

    List<Tag> findAll() throws SQLException;

    List<Offer> findOffers(Long tagId)throws SQLException;

    Tag updateTag(Tag tag) throws SQLException;

    void deleteTag(Long id) throws SQLException;

}
