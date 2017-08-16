package com.odmytrenko.controller;

import com.odmytrenko.model.Product;
import com.odmytrenko.service.ProductService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class ProductController implements Controller {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        try {
            Product product = productService.findById(Long.parseLong(request.getParameter("id")));
            return new ViewModel("product").addAttribute("product", product).
                    addAttribute("categoryId", product.getCategory().getId());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid id, please specify correct number of id");
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("There is no such id, please specify correct number of id");
        }
    }
}
