package com.odmytrenko.controller;

import com.odmytrenko.model.Product;
import com.odmytrenko.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void findByIdAndCategoryName(HttpServletRequest request, HttpServletResponse response, Long id, String categoryName) throws ServletException, IOException {
        request.setAttribute("product", productService.findByIdAndCategoryName(id, categoryName));
    }

    public void create(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String categoryName = request.getParameter("productCategoryName");
        Product requestedProduct = new Product(null, name, description, categoryName);
        request.setAttribute("object", productService.create(requestedProduct));
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String categoryName = request.getParameter("productCategoryName");
        Product requestedProduct = new Product(null, name, description, categoryName);
        request.setAttribute("object", productService.delete(requestedProduct));
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String categoryName = request.getParameter("productCategoryName");
        Product requestedProduct = new Product(null, name, description, categoryName);
        request.setAttribute("object", productService.update(requestedProduct));
    }
}
