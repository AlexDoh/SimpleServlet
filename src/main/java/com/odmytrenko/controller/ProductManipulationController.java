package com.odmytrenko.controller;

import com.odmytrenko.model.Product;
import com.odmytrenko.service.ProductService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class ProductManipulationController implements Controller {

    private final ProductService productService;

    public ProductManipulationController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String categoryName = request.getParameter("productCategoryName");

        return CrudController.process(request, productService, new Product(null, name, description, categoryName));
    }
}
