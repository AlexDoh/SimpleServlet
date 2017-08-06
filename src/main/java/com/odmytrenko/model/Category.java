package com.odmytrenko.model;

import java.util.List;

public class Category extends Model {

    private List<Product> productList;

    public Category(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
