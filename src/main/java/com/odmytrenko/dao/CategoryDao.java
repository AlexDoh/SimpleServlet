package com.odmytrenko.dao;

import com.odmytrenko.model.Category;

import java.util.Set;

public interface CategoryDao extends GenericDao<Category> {

    Set<Category> getAll();
}
