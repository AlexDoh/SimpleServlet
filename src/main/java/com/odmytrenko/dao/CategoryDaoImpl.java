package com.odmytrenko.dao;

import com.odmytrenko.model.Category;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    private static List<Category> categoryList = new ArrayList<>();

    static {
        Category category1 = new Category("Shoes");
        category1.setId(1L);
        Category category2 = new Category("Dresses");
        category2.setId(2L);
        Category category3 = new Category("Pants");
        category3.setId(3L);
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
    }

    public CategoryDaoImpl(Connection connection) {
        super(connection);
    }


    @Override
    public Category create(Category category) {
        category.setId(categoryList.stream().max(Comparator.comparing(Category::getId)).get().getId() + 1);
        categoryList.add(category);
        return category;
    }

    @Override
    public Category delete(Category category) {
        categoryList.removeIf(i -> i.getName().equals(category.getName()));
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
