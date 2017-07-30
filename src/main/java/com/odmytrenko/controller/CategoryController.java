package com.odmytrenko.controller;

import com.odmytrenko.model.Category;
import com.odmytrenko.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void findById(HttpServletRequest request, HttpServletResponse response, Long id) throws ServletException, IOException {
        request.setAttribute("category", categoryService.findById(id));
    }

    public void create(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter("categoryName");
        Category requestedCategory = new Category(null, categoryName, null);
        request.setAttribute("object", categoryService.create(requestedCategory));
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter("categoryName");
        Category requestedCategory = new Category(null, categoryName, null);
        request.setAttribute("object", categoryService.delete(requestedCategory));
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter("categoryName");
        Category requestedCategory = new Category(null, categoryName, null);
        request.setAttribute("object", categoryService.update(requestedCategory));
    }
}
