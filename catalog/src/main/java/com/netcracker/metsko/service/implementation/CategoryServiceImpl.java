package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.dao.CategoryDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.ExceptionMessage;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    public void createCategory(Category category) throws NotCreatedException, SQLException {
        try {
            categoryDao.create(category);
        } catch (Exception e) {
            throw new NotCreatedException("Category" + ExceptionMessage.NOT_CREATED + " " + ExceptionMessage.NOT_NULL_ENTITY);
        }
    }

    @Override
    public Category findById(Long id) throws NotFoundException, SQLException {
        try {
            Category category = (Category) categoryDao.read(id);
            return category;
        } catch (Exception e) {
            throw new NotFoundException("This category" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public Category findByName(String name) throws SQLException, NotFoundException {
        try {
            return categoryDao.findByName(name);
        } catch (Exception e) {
            throw new NotFoundException("Category" + ExceptionMessage.NOT_FOUND);
        }
    }

    @Override
    public List<Category> findAll() throws SQLException, NotFoundException {
        try {
            return categoryDao.findAll();
        } catch (Exception e) {
            throw new NotFoundException("Categories " + ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Category updateCategory(Category category) throws SQLException, NotUpdatedException {
        try {
            return (Category) categoryDao.update(category);
        } catch (Exception e) {
            throw new NotUpdatedException("This category" + ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public String deleteCategory(Long categoryId) throws SQLException, NotDeletedException {
        try {
            Category category = (Category) categoryDao.read(categoryId);
            if (category.getOfferList().size() > 0) {
                return "The category is not empty and can't be deleted.";
            } else {
                categoryDao.delete(categoryId);
                return "The category is deleted.";
            }
        } catch (Exception e) {
            throw new NotDeletedException("The category " + ExceptionMessage.NOT_DELETED);
        }
    }

    @Override
    public List<Offer> findOfferList(Long id) throws SQLException, NotFoundException {
        try {
            List<Offer> offerList = categoryDao.findOfferList(id);
            return offerList;
        } catch (Exception e) {
            throw new NotFoundException("Offers " + ExceptionMessage.NOT_FOUND);
        }
    }
}
