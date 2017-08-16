package com.odmytrenko.controller;

import com.odmytrenko.service.CategoryService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class AdminController implements Controller {

    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        return new ViewModel("adminConsole").addAttribute("categories", categoryService.getAll());
    }
}
