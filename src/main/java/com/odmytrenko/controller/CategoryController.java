package com.odmytrenko.controller;

import com.odmytrenko.service.CategoryService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class CategoryController implements Controller {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        try {
            return new ViewModel("category").addAttribute("category", categoryService.findById(Long.parseLong(request.getParameter("id"))));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid id, please specify correct number of id");
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("There is no such id, please specify correct number of id");
        }
    }
}
