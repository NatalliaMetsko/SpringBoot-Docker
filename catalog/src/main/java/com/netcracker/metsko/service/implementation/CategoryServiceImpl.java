package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.CategoryDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    public void createCategory(Category category) throws SQLException {
        categoryDao.create(category);
    }

    @Transactional
    public Category findById(Long id) throws SQLException {

        return (Category) categoryDao.read(id);
    }

    @Transactional
    public Category findByName(String name)throws SQLException {
        return categoryDao.findByName(name);
    }

    @Transactional
    public List<Category> findAll() throws SQLException {
        return categoryDao.findAll();
    }

    @Transactional
    public Category updateCategory(Category category)throws SQLException {
        return (Category) categoryDao.update(category);
    }

    @Transactional
    public void deleteCategory(Long categoryId) throws SQLException {
            categoryDao.delete(categoryId);
    }

    @Transactional
    public List<Offer> findCategoryOffers(Category category)throws SQLException {
        return categoryDao.findOfferList(category);
    }
}
