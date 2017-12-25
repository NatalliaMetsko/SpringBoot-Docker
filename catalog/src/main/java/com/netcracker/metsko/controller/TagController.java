package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.ExceptionMessage;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/v1/catalog/tags")
@Api(value = "Controller", description = "This is tag controller")
public class TagController {

    @Autowired
    private TagService tagService;

    public TagController() {
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST",
            value = "Create a tag",
            response = Tag.class,
            nickname = "createTag")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tag created"),
            @ApiResponse(code = 500, message = "Tag not created")
    })
    public ResponseEntity<Tag> createTag(@Validated @RequestBody Tag tag) throws NotCreatedException, SQLException {
        try {
            if (tag.getTag().length() != 0) {
                tagService.createTag(tag);
                return new ResponseEntity<>(tag, HttpStatus.CREATED);
            } else {
                throw new NotCreatedException(ExceptionMessage.NULL_FIELDS);
            }
        }catch (Exception e){
            throw new NotCreatedException(ExceptionMessage.NOT_CREATED);
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(httpMethod = "GET",
            value = "Find a tag by id",
            response = Tag.class,
            nickname = "findById")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Tag found"),
            @ApiResponse(code = 404, message = "Tag not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Tag> findById(@PathVariable("id") Long id) throws NotFoundException, SQLException {
        try {
            Tag tag = tagService.findTagById(id);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        }catch (Exception e){
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping(value = "/name")
    @ApiOperation(httpMethod = "GET",
            value = "Find a tag by it's name",
            response = Tag.class,
            nickname = "findByName")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Tag found"),
            @ApiResponse(code = 404, message = "Tag not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Tag> findByName(@RequestParam("name") String name) throws NotFoundException, SQLException {
        try {
            Tag tag = tagService.findTagByName(name);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        }catch (Exception e){
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET",
            value = "Find all tags",
            response = Tag.class,
            nickname = "findAll",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Tags found"),
            @ApiResponse(code = 404, message = "Tags not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Tag>> findAll() throws NotFoundException, SQLException {
        try {
            List<Tag> tagList = tagService.findAll();
            return new ResponseEntity<>(tagList, HttpStatus.OK);
        }catch (Exception e){
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/offers")
    @ApiOperation(httpMethod = "GET",
            value = "Find offers belonged to the tag (by id)",
            response = Offer.class,
            nickname = "findOffers",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Offers found"),
            @ApiResponse(code = 404, message = "Tag not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Offer>> findOffers(@PathVariable("id") Long id) throws NotFoundException, SQLException {
        try {
            List<Offer> offerList = tagService.findOffers(id);
            return new ResponseEntity<>(offerList, HttpStatus.OK);
        }catch (Exception e){
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @PutMapping
    @ApiOperation(httpMethod = "PUT",
            value = "Update tag",
            response = Tag.class,
            nickname = "updateTag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tag updated"),
            @ApiResponse(code = 404, message = "Tag not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag) throws NotUpdatedException, SQLException {
        try {
            if (tag.getTag().length() != 0 && tagService.findTagById(tag.getId()) != null) {
                Tag updatedTag = tagService.updateTag(tag);
                return new ResponseEntity<>(updatedTag, HttpStatus.OK);
            } else {
                throw new NotUpdatedException(ExceptionMessage.NULL_FIELDS);
            }
        } catch (NotFoundException e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }

    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(httpMethod = "DELETE",
            value = "Delete a tag by id",
            response = Long.class,
            nickname = "deleteTag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tag deleted"),
            @ApiResponse(code = 404, message = "Tag not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<String> deleteTag(@PathVariable("id") Long id) throws NotDeletedException, SQLException {
        try {
            tagService.deleteTag(id);
            return new ResponseEntity<>("The tag deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new NotDeletedException(ExceptionMessage.NOT_DELETED);
        }

    }
}
