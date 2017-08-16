package com.odmytrenko.service;

import com.odmytrenko.model.Product;

import java.util.Set;

public interface ProductService extends CrudService<Product> {

    Set<Product> getAll();

    Set<Product> getAllByCategoryId(Long categoryId);

    Product create(Product product);

    Product delete(Product product);

    Product update(Product product);

    Product findById(Long id);
}
