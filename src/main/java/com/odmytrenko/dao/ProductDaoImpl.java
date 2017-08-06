package com.odmytrenko.dao;

import com.odmytrenko.model.Product;

import java.util.*;

public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {

    private static Map<String, List<Product>> productMap = new HashMap<>();

    static {
        List<Product> productListForShoes = new ArrayList<>();
        productListForShoes.add(new Product(1L, "Low shoe", "HighQualifiedProduct", "Shoes"));
        productListForShoes.add(new Product(2L, "Sandals", "MiddleQualifiedProduct", "Shoes"));
        productListForShoes.add(new Product(3L, "Boots", "LowQualifiedProduct", "Shoes"));
        List<Product> productListForDresses = new ArrayList<>();
        productListForDresses.add(new Product(1L, "Shift", "HighQualifiedProduct", "Dresses"));
        productListForDresses.add(new Product(2L, "Doll", "MiddleQualifiedProduct", "Dresses"));
        productListForDresses.add(new Product(3L, "Kimono", "LowQualifiedProduct", "Dresses"));
        List<Product> productListForPants = new ArrayList<>();
        productListForPants.add(new Product(1L, "Breeches", "HighQualifiedProduct", "Pants"));
        productListForPants.add(new Product(2L, "Trousers", "MiddleQualifiedProduct", "Pants"));
        productListForPants.add(new Product(3L, "Drawers", "LowQualifiedProduct", "Pants"));

        productMap.put("Shoes", productListForShoes);
        productMap.put("Dresses", productListForDresses);
        productMap.put("Pants", productListForPants);
    }

    @Override
    public List<Product> getAllProductsByCategory(String categoryName) {
        return productMap.get(categoryName);
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Product findByIdAndCategoryName(Long id, String categoryName) {
        return productMap.get(categoryName).get(id.intValue() - 1);
    }

    @Override
    public Product create(Product product) {
        if (productMap.get(product.getCategoryName()) != null) {
            product.setId((productMap.get(product.getCategoryName()).stream().max(Comparator.comparing(Product::getId)).get().getId()) + 1);
        } else {
            productMap.put(product.getCategoryName(), new ArrayList<>());
            product.setId(1L);
        }
        productMap.get(product.getCategoryName()).add(product);
        return product;
    }

    @Override
    public Product delete(Product product) {
        productMap.get(product.getCategoryName()).remove(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        productMap.get(product.getCategoryName()).removeIf(i -> i.getName().equals(product.getName()));
        productMap.get(product.getCategoryName()).add(product);
        return product;
    }
}
