package com.netcracker.metsko.service;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {

    void createCategory(Category category) throws SQLException, NotCreatedException;

    Category findById(Long id) throws SQLException, NotFoundException;

    Category findByName(String name) throws SQLException, NotFoundException;

    List<Category> findAll() throws SQLException, NotFoundException;

    Category updateCategory(Category category) throws SQLException, NotUpdatedException;

    void deleteCategory(Long categoryId) throws SQLException, NotDeletedException;

    List<Offer> findOfferList(Long categoryId) throws SQLException, NotFoundException;

}
