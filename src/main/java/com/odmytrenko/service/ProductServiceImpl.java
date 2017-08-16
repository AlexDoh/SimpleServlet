package com.odmytrenko.service;

import com.odmytrenko.dao.ProductDao;
import com.odmytrenko.model.Product;

import java.util.Set;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Set<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Set<Product> getAllByCategoryId(Long categoryId) {
        return productDao.getAllByCategoryId(categoryId);
    }

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product delete(Product product) {
        return productDao.delete(product);
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

}
