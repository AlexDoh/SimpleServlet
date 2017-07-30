package com.odmytrenko.dao;

import com.odmytrenko.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    private static List<Category> categoryList = new ArrayList<>();

    static {
        categoryList.add(new Category(1L, "Shoes", null));
        categoryList.add(new Category(2L, "Dresses", null));
        categoryList.add(new Category(3L, "Pants", null));
    }


    @Override
    public Category create(Category category) {
        category.setId(categoryList.get(categoryList.size() - 1).getId() + 1);
        categoryList.add(category);
        return category;
    }

    @Override
    public Category delete(Category category) {
        categoryList.remove(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        categoryList.removeIf(i -> i.getName().equals(category.getName()));
        categoryList.add(category);
        return category;
    }

    @Override
    public Category findById(Long id) {
        return categoryList.get(id.intValue() - 1);
    }

    @Override
    public List<Category> getAll() {
        return CategoryDaoImpl.categoryList;
    }
}
