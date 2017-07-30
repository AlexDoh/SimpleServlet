package com.odmytrenko.service;

import com.odmytrenko.dao.CategoryDao;
import com.odmytrenko.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category create(Category category) {
        return categoryDao.create(category);
    }

    @Override
    public Category delete(Category category) {
        return categoryDao.delete(category);
    }

    @Override
    public Category update(Category category) {
        return categoryDao.update(category);
    }
}
