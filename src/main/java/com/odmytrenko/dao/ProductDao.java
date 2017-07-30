package com.odmytrenko.dao;

import com.odmytrenko.model.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {

    List<Product> getAllProductsByCategory(String categoryName);

    Product findByIdAndCategoryName(Long id, String categoryName);

}
