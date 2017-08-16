package com.odmytrenko.controller;

import com.odmytrenko.model.Category;
import com.odmytrenko.model.Product;
import com.odmytrenko.service.CategoryService;
import com.odmytrenko.service.ProductService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class ProductManipulationController implements Controller {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductManipulationController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        String name = request.getParameter("productName");
        String description;
        if (request.hasParameter("productDescription")) {
            description = request.getParameter("productDescription");
        } else {
            description = null;
        }
        Category category = categoryService.findById(Long.parseLong(request.getParameter("productCategoryId")));

        return CrudController.process(request, productService, new Product(name, description, category));
    }
}
