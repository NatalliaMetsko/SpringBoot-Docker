package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/v1/catalog/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController() {
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category newCategory) throws NotCreatedException, SQLException {
        categoryService.createCategory(newCategory);
        return  new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long categoryId) throws NotFoundException, SQLException {
        Category category = categoryService.findById(categoryId);
        return new ResponseEntity<Category>(category, HttpStatus.FOUND);

    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Category> findByName(@PathVariable("name") String name) throws NotFoundException, SQLException {
        Category category = categoryService.findByName(name);
        return new ResponseEntity<Category>(category, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() throws NotFoundException, SQLException {
        List<Category> categoryList= categoryService.findAll();
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws NotUpdatedException, SQLException {
        Category updatedCategory = categoryService.updateCategory(category);
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Long> deleteCategory(@PathVariable("id") Long categoryId) throws NotDeletedException, SQLException {
        categoryService.deleteCategory(categoryId);
        return  new ResponseEntity<Long>(categoryId, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/offers")
    public ResponseEntity<List<Offer>> findOfferList(@PathVariable("id") Long id) throws NotFoundException, SQLException {
        List<Offer> offerList= categoryService.findOfferList(id);
        return new ResponseEntity<List<Offer>>(offerList, HttpStatus.FOUND);
    }
}