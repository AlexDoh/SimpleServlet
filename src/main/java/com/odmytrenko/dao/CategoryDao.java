package com.odmytrenko.dao;

import com.odmytrenko.model.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category> {

    List<Category> getAll();
}
