package com.odmytrenko.controller;

import com.odmytrenko.model.Category;
import com.odmytrenko.service.CategoryService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class CategoryManipulationController implements Controller {

    private CategoryService categoryService;

    public CategoryManipulationController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        String categoryName = request.getParameter("categoryName");

        return CrudController.process(request, categoryService, new Category(null, categoryName, null));
    }
}
