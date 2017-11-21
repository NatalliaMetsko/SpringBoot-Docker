package com.netcracker.metsko.service.implementation;


import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    private final TagDaoImpl tagDao;

    @Autowired
    public TagDaoImpl getTagDaoImpl()
    {
        return new TagDaoImpl();
    }

    @Transactional
    public void createTag(Tag tag) {
        tagDao.create(tag);
    }

    @Transactional
    public Tag findTagById(Long id) {
        return tagDao.read(id);
    }

    @Transactional
    public Tag findTagByName(String tagName) {
        return tagDao.findByName(tagName);
    }

    @Transactional
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Transactional
    public List<Offer> findOffers(Long id) {
        Tag tag = tagDao.read(id);
        return tag.getOfferList();
    }

    @Transactional
    public Tag updateTag(Tag tag) {
        return tagDao.update(tag);
    }

    @Transactional
    public void deleteTag(Tag tag) {
        tagDao.delete(tag);
    }
}
