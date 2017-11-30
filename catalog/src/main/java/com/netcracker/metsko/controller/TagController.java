package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/v1/catalog/tags")
public class TagController {

    @Autowired
    private TagService tagService;


    public TagController() {
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) throws NotCreatedException, SQLException{
        tagService.createTag(tag);
        return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tag> findById(@PathVariable("id") Long id) throws NotFoundException, SQLException
    {
        Tag tag = tagService.findTagById(id);
        return new ResponseEntity<Tag>(tag, HttpStatus.FOUND);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Tag> findByName(@PathVariable("name") String name) throws NotFoundException, SQLException{
        Tag tag = tagService.findTagByName(name);
        return new ResponseEntity<Tag>(tag, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> findAll() throws NotFoundException, SQLException
    {
        List<Tag> tagList = tagService.findAll();
        return new ResponseEntity<List<Tag>>(tagList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}/offers")
    public ResponseEntity<List<Offer>> findOffers(@PathVariable("id") Long id) throws NotFoundException, SQLException
    {
        List<Offer> offerList = tagService.findOffers(id);
        return new ResponseEntity<List<Offer>>(offerList, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag) throws NotUpdatedException, SQLException{
        Tag updatedTag = tagService.updateTag(tag);
        return new ResponseEntity<Tag>(updatedTag, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteTag(@PathVariable("id") Long id) throws NotDeletedException, SQLException{
        tagService.deleteTag(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
