package com.odmytrenko.service;

import com.odmytrenko.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProductsByCategory(String categoryName);

    Product findByIdAndCategoryName(Long id, String categoryName);

    Product create(Product product);

    Product delete(Product product);

    Product update(Product product);
}
