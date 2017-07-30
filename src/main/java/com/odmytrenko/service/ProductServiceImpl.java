package com.odmytrenko.service;

import com.odmytrenko.dao.ProductDao;
import com.odmytrenko.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProductsByCategory(String categoryName) {
        return productDao.getAllProductsByCategory(categoryName);
    }

    @Override
    public Product findByIdAndCategoryName(Long id, String categoryName) {
        return productDao.findByIdAndCategoryName(id, categoryName);
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

}
