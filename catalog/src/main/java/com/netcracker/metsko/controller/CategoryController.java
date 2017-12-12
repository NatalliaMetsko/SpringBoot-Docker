package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.ExceptionMessage;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.service.CategoryService;
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
@RequestMapping("api/v1/catalog/categories")
@Api(value = "Controller", description = "This is category controller")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController() {
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST",
            value = "Create a category",
            response = Category.class,
            nickname = "createCategory")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category created"),
            @ApiResponse(code = 500, message = "Category not created")
    })
    public ResponseEntity<Category> createCategory(@Validated @RequestBody Category newCategory) throws NotCreatedException, SQLException {
        if (newCategory.getCategory().length() != 0) {
            categoryService.createCategory(newCategory);
            return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
        } else {
            throw new NotCreatedException(ExceptionMessage.NULL_FIELDS);
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(httpMethod = "GET",
            value = "Find a category by id",
            response = Category.class,
            nickname = "findById")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Category found"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Category> findById(@PathVariable("id") Long categoryId) throws NotFoundException, SQLException {
        try {
            Category category = categoryService.findById(categoryId);
            return new ResponseEntity<Category>(category, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }

    }

    @GetMapping(value = "/name/{name}")
    @ApiOperation(httpMethod = "GET",
            value = "Find a category by it's name",
            response = Category.class,
            nickname = "findByName")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Category found"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Category> findByName(@PathVariable("name") String name) throws NotFoundException, SQLException {
        try {
            Category category = categoryService.findByName(name);
            return new ResponseEntity<Category>(category, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET",
            value = "Find all categories",
            response = Category.class,
            nickname = "findAll",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Categories found"),
            @ApiResponse(code = 404, message = "Categories not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Category>> findAll() throws NotFoundException, SQLException {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.FOUND);
    }

    @PutMapping
    @ApiOperation(httpMethod = "PUT",
            value = "Update category",
            response = Category.class,
            nickname = "updateCategory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 500, message = "Category not updated")
    })
    public ResponseEntity<Category> updateCategory(@Validated @RequestBody Category category) throws NotUpdatedException, SQLException, NotFoundException {

        try {
            if (category.getCategory().length() != 0 && categoryService.findById(category.getId()) != null) {
                Category updatedCategory = categoryService.updateCategory(category);
                return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
            } else {
                throw new NotUpdatedException(ExceptionMessage.NULL_FIELDS);
            }
        } catch (NotFoundException e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(httpMethod = "DELETE",
            value = "Delete a category by id",
            response = Long.class,
            nickname = "deleteCategory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category deleted"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> deleteCategory(@PathVariable("id") Long categoryId) throws NotDeletedException, SQLException {
        try {
            categoryService.deleteCategory(categoryId);
            return new ResponseEntity<Long>(categoryId, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotDeletedException(ExceptionMessage.NOT_DELETED);
        }
    }

    @GetMapping(value = "/{id}/offers")
    @ApiOperation(httpMethod = "GET",
            value = "Find offers belonged to the category (by id)",
            response = Offer.class,
            nickname = "findOfferList",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Offers found"),
            @ApiResponse(code = 404, message = "Offers not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Offer>> findOfferList(@PathVariable("id") Long id) throws NotFoundException, SQLException {
        try {
            List<Offer> offerList = categoryService.findOfferList(id);
            return new ResponseEntity<List<Offer>>(offerList, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND);
        }
    }
}