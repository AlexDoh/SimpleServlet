package com.odmytrenko.controller;

import com.odmytrenko.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAllProductsController {

    private ProductService productService;

    public GetAllProductsController(ProductService categoryService) {
        this.productService = categoryService;
    }

    public void getAllProducts(HttpServletRequest request, HttpServletResponse response, String categoryName) throws ServletException, IOException {
        request.setAttribute("products", productService.getAllProductsByCategory(categoryName));
    }
}
