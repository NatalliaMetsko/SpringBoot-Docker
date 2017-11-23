package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/catalog/categories")
public class CategoryService {

    @Autowired
    private CategoryService categoryService;

    public CategoryService() {
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody Category newCategory){
        categoryService.createCategory(newCategory);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Category findById(@PathVariable("id") Long categoryId)
    {
        return categoryService.findById(categoryId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public Category findByName(@PathVariable("name") String name)
    {
        return  categoryService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Category> findAll()
    {
        return  categoryService.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@RequestBody Category category)
    {
        return  categoryService.updateCategory(category);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") Long categoryId)
    {
        categoryService.deleteCategory(categoryId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Offer> findOfferList(@RequestBody Category category)
    {
        return categoryService.findOfferList(category);
    }


}
