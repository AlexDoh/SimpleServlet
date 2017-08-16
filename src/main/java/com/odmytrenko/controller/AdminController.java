package com.odmytrenko.controller;

import com.odmytrenko.service.CategoryService;
import com.odmytrenko.service.ProductService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class AdminController implements Controller {

    private final CategoryService categoryService;
    private final ProductService productService;

    public AdminController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        return new ViewModel("adminConsole").
                addAttribute("categories", categoryService.getAll()).
                addAttribute("products", productService.getAll());
    }
}
