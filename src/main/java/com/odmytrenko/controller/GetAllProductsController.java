package com.odmytrenko.controller;

import com.odmytrenko.service.CategoryService;
import com.odmytrenko.service.ProductService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class GetAllProductsController implements Controller {

    private final ProductService productService;
    private final CategoryService categoryService;

    public GetAllProductsController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        return new ViewModel("products").
                addAttribute("products", productService.getAllByCategoryId(Long.
                        parseLong(request.getParameter("categoryId")))).
                addAttribute("category", categoryService.findById(Long.
                        parseLong(request.getParameter("categoryId"))));
    }
}
