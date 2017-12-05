package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/v1/catalog/categories")
public class    CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController() {
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST",
            value = "Create a category",
            response = Category.class,
            nickname="createCategory")
//  @ApiResponse(code = 500, message = "Category not created")
    public ResponseEntity<Category> createCategory(@RequestBody Category newCategory) throws NotCreatedException, SQLException {
        categoryService.createCategory(newCategory);
        return  new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(httpMethod = "GET",
            value = "Find a category by id",
            response = Category.class,
            nickname="findById")
//    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
//            @ApiResponse(code = 404, message = "Category not found") })
    public ResponseEntity<Category> findById(@PathVariable("id") Long categoryId) throws NotFoundException, SQLException {
        Category category = categoryService.findById(categoryId);
        return new ResponseEntity<Category>(category, HttpStatus.FOUND);

    }

    @GetMapping(value = "/name/{name}")
    @ApiOperation(httpMethod = "GET",
            value = "Find a category by it's name",
            response = Category.class,
            nickname="findByName")
    public ResponseEntity<Category> findByName(@PathVariable("name") String name) throws NotFoundException, SQLException {
        Category category = categoryService.findByName(name);
        return new ResponseEntity<Category>(category, HttpStatus.FOUND);
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET",
            value = "Find all categories",
            response = Category.class,
            nickname="findAll",
            responseContainer = "List")
    public ResponseEntity<List<Category>> findAll() throws NotFoundException, SQLException {
        List<Category> categoryList= categoryService.findAll();
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.FOUND);
    }

    @PutMapping
    @ApiOperation(httpMethod = "PUT",
            value = "Update category",
            response = Category.class,
            nickname="updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws NotUpdatedException, SQLException {
        Category updatedCategory = categoryService.updateCategory(category);
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping( value = "/{id}")
    @ApiOperation(httpMethod = "DELETE",
            value = "Delete a category by id",
            response = Long.class,
            nickname="deleteCategory")
    public ResponseEntity<Long> deleteCategory(@PathVariable("id") Long categoryId) throws NotDeletedException, SQLException {
        categoryService.deleteCategory(categoryId);
        return  new ResponseEntity<Long>(categoryId, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/offers")
    @ApiOperation(httpMethod = "GET",
            value = "Find offers belonged to the category (by id)",
            response = Offer.class,
            nickname="findOfferList",
            responseContainer = "List")
    public ResponseEntity<List<Offer>> findOfferList(@PathVariable("id") Long id) throws NotFoundException, SQLException {
        List<Offer> offerList= categoryService.findOfferList(id);
        return new ResponseEntity<List<Offer>>(offerList, HttpStatus.FOUND);
    }
}