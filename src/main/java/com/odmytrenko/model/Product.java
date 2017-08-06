package com.odmytrenko.model;

public class Product extends Model {

    private String description;
    private String categoryName;

    public Product(String name, String description, String categoryName) {
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
