package com.netcracker.metsko.service.implementation;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryDaoImpl categoryDao;

    @Autowired
    public CategoryDaoImpl getCategoryDaoImpl()
    {
         return new CategoryDaoImpl();
    }

    @Transactional
    public Category createCategory(Category category) {
        return categoryDao.create(category);
    }

    @Transactional
    public Category findById(Long id) {
        return categoryDao.read(id);
    }

    @Transactional
    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Transactional
    public Category findAll() {
        return categoryDao.findAll();
    }

    @Transactional
    public Category updateCategory(Category category) {
        return categoryDao.update(category);
    }

    @Transactional
    public void deleteCategory(Category category) {
            categoryDao.delete(category);
    }

    @Transactional
    public List<Offer> findCategoryOffers(Category category) {
        return categoryDao.findOfferList(category);
    }
}
