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
    public void createTag(Tag tag)throws NotCreatedException, SQLException {
        try{
            tagDao.create(tag);
        }catch (Exception e)
        {
            throw new NotCreatedException("The tag");
        }
    }

    @Transactional
    public Tag findTagById(Long id) throws NotFoundException, SQLException {
        Tag tag = (Tag) tagDao.read(id);
        if(tag!=null)
        {
            return  tag;
        }
        else {
            throw new NotFoundException("This tag");
        }
    }

    @Transactional
    public Tag findTagByName(String tagName) throws NotFoundException,SQLException {
        Tag tag = tagDao.findByName(tagName);
        if(tag!=null)
        {
            return tag;
        }
        else{
            throw new NotFoundException("The tag");
        }

    }

    @Override
    public List<Tag> findAll() throws NotFoundException, SQLException {
        List<Tag> tagList=tagDao.findAll();
        if(tagList!=null)
        {
            return tagList;
        }
        else{
            throw new NotFoundException("Tags");
        }
    }

    @Transactional
    public List<Offer> findOffers(Long tagId) throws NotFoundException, SQLException {
        Tag tag = (Tag) tagDao.read(tagId);
        if(tag!=null){
            return tag.getOfferList();
        }
        else{
            throw new NotFoundException("The offers");
        }

    }

    @Transactional
    public Tag updateTag(Tag tag) throws NotUpdatedException, SQLException {
        Tag updatedTag = (Tag) tagDao.update(tag);
        if(updatedTag!=null)
        {
            return updatedTag;
        }
        else
        {
            throw new NotUpdatedException("The tag");
        }
    }

    @Transactional
    public void deleteTag(Long tagId) throws NotDeletedException, SQLException {
        try{
            tagDao.delete(tagId);
        }
        catch (Exception e)
        {
            throw new NotDeletedException("the tag");
        }
    }
}
