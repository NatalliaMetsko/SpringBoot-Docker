package com.netcracker.metsko.service.implementation;


import com.netcracker.metsko.dao.TagDao;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDao tagDao;

    @Transactional
    public void createTag(Tag tag)throws SQLException {
        tagDao.create(tag);
    }

    @Transactional
    public Tag findTagById(Long id) throws SQLException {
        return (Tag) tagDao.read(id);
    }

    @Transactional
    public Tag findTagByName(String tagName) throws SQLException {
        return tagDao.findByName(tagName);
    }

    @Override
    public List<Tag> findAll() throws SQLException {
        return tagDao.findAll();
    }

    @Transactional
    public List<Offer> findOffers(Long tagId) throws SQLException {
        Tag tag = (Tag) tagDao.read(tagId);
        return tag.getOfferList();
    }

    @Transactional
    public Tag updateTag(Tag tag) throws SQLException {
        return (Tag) tagDao.update(tag);
    }

    @Transactional
    public void deleteTag(Tag tag) throws SQLException {
        tagDao.delete(tag);
    }
}
