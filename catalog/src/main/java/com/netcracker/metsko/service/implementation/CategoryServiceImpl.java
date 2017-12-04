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
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    public void createCategory(Category category) throws NotCreatedException {
            try
            {
                categoryDao.create(category);
            }
            catch (Exception e)
            {
                throw new NotCreatedException("Category"+ ExceptionMessage.NOT_CREATED);
            }
    }

    @Transactional
    public Category findById(Long id) throws NotFoundException, SQLException {
        Category category = (Category) categoryDao.read(id);

        if (category== null) {

            throw new NotFoundException("This category"+ExceptionMessage.NOT_FOUND);

        }
        else{
            return category;
        }
    }
    @Transactional
    public Category findByName(String name) throws SQLException, NotFoundException {
        Category category = categoryDao.findByName(name);
        if (category !=null) {
            return categoryDao.findByName(name);
        }
        else{
            throw new NotFoundException("Category"+ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public List<Category> findAll() throws SQLException, NotFoundException {
        List<Category> categoryList = categoryDao.findAll();
        if (categoryList!=null)
        {
            return  categoryList;
        }
        else{
            throw new NotFoundException("Category "+ExceptionMessage.NOT_FOUND);
        }
    }

    @Transactional
    public Category updateCategory(Category category)throws SQLException, NotUpdatedException {
        Category updatedCategory= (Category) categoryDao.update(category);
        if(updatedCategory!=null)
        {
            return  updatedCategory;
        }
        else
        {
            throw  new NotUpdatedException("This category"+ExceptionMessage.NOT_UPDATED);
        }
    }

    @Transactional
    public void deleteCategory(Long categoryId) throws SQLException, NotDeletedException {
            try{
                categoryDao.delete(categoryId);
            }
            catch (Exception e)
            {
                throw new NotDeletedException("The category "+ExceptionMessage.NOT_DELETED);
            }
    }

    @Transactional
    public List<Offer> findOfferList(Long id) throws SQLException, NotFoundException {
        List<Offer> offerList =categoryDao.findOfferList(id);
        if (offerList!=null)
        {
            return  offerList;
        }
        else
        {
            throw new NotFoundException("Offers "+ExceptionMessage.NOT_FOUND);
        }
    }
}
