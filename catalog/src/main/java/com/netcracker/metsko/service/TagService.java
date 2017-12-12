package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface TagService {

    void createTag(Tag tag) throws SQLException, NotCreatedException;

    Tag findTagById(Long id) throws SQLException, NotFoundException;

    Tag findTagByName(String tagName) throws SQLException, NotFoundException;

    List<Tag> findAll() throws SQLException, NotFoundException;

    List<Offer> findOffers(Long tagId) throws SQLException, NotFoundException;

    Tag updateTag(Tag tag) throws SQLException, NotUpdatedException;

    void deleteTag(Long id) throws SQLException, NotDeletedException;

}
