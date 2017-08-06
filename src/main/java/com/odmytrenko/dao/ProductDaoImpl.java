package com.odmytrenko.dao;

import com.odmytrenko.model.Model;
import com.odmytrenko.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {

    private static Map<String, List<Product>> productMap = new HashMap<>();

    static {
        List<Product> productListForShoes = new ArrayList<>();
        Product product1 = new Product("Low shoe", "HighQualifiedProduct", "Shoes");
        product1.setId(1L);
        Product product2 = new Product("Sandals", "MiddleQualifiedProduct", "Shoes");
        product2.setId(2L);
        Product product3 = new Product("Boots", "LowQualifiedProduct", "Shoes");
        product3.setId(3L);
        productListForShoes.add(product1);
        productListForShoes.add(product2);
        productListForShoes.add(product3);
        List<Product> productListForDresses = new ArrayList<>();
        Product product4 = new Product("Shift", "HighQualifiedProduct", "Dresses");
        product1.setId(1L);
        Product product5 = new Product("Doll", "MiddleQualifiedProduct", "Dresses");
        product2.setId(2L);
        Product product6 = new Product("Kimono", "LowQualifiedProduct", "Dresses");
        product3.setId(3L);
        productListForDresses.add(product4);
        productListForDresses.add(product5);
        productListForDresses.add(product6);
        List<Product> productListForPants = new ArrayList<>();
        Product product7 = new Product("Breeches", "HighQualifiedProduct", "Pants");
        product1.setId(1L);
        Product product8 = new Product("Trousers", "MiddleQualifiedProduct", "Pants");
        product2.setId(2L);
        Product product9 = new Product("Drawers", "LowQualifiedProduct", "Pants");
        product3.setId(3L);
        productListForPants.add(product7);
        productListForPants.add(product8);
        productListForPants.add(product9);

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
        productMap.get(product.getCategoryName()).removeIf(i -> i.getName().equals(product.getName()));
        return product;
    }

    @Override
    public Product update(Product product) {
        productMap.get(product.getCategoryName()).removeIf(i -> i.getName().equals(product.getName()));
        productMap.get(product.getCategoryName()).add(product);
        return product;
    }
}
