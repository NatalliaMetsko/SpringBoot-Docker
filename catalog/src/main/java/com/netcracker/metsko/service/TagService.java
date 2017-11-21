package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;

import javax.persistence.Table;
import java.util.List;

public interface TagService {

    void createTag(Tag tag);

    Tag findTagById(Long id);

    Tag findTagByName(String tagName);

    List<Tag> findAll();

    List<Offer> findOffers(Tag tag);

    Tag updateTag(Tag tag);

    void deleteTag(Tag tag);

}
