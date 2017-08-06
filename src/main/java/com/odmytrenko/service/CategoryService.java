package com.odmytrenko.service;

import com.odmytrenko.model.Category;

import java.util.List;

public interface CategoryService extends CrudService<Category> {

    List<Category> getAll();

    Category findById(Long id);

    Category create(Category category);

    Category delete(Category category);

    Category update(Category category);

}
