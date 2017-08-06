package com.odmytrenko.controller;

import com.odmytrenko.service.ProductService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class GetAllProductsController implements Controller {

    private ProductService productService;

    public GetAllProductsController(ProductService categoryService) {
        this.productService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        return new ViewModel("products").addAttribute("products", productService.getAllProductsByCategory(request.getParameter("category")));
    }
}
