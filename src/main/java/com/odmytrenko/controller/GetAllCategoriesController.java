package com.odmytrenko.controller;

import com.odmytrenko.service.CategoryService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class GetAllCategoriesController implements Controller {

    private CategoryService categoryService;

    public GetAllCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        return new ViewModel("categories").addAttribute("categories", categoryService.getAll());
    }
}
