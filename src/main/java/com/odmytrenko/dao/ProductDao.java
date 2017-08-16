package com.odmytrenko.dao;

import com.odmytrenko.model.Product;

import java.util.Set;

public interface ProductDao extends GenericDao<Product> {

    Set<Product> getAll();

    Set<Product> getAllByCategoryId(Long categoryId);

}
