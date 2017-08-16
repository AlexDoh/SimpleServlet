package com.odmytrenko.service;

import com.odmytrenko.model.Category;

import java.util.Set;

public interface CategoryService extends CrudService<Category> {

    Set<Category> getAll();

    Category findById(Long id);

    Category create(Category category);

    Category delete(Category category);

    Category update(Category category);

}
