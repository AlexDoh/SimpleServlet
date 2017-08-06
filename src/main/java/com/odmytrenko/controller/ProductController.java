package com.odmytrenko.controller;

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
            return new ViewModel("product").addAttribute("product", productService.findByIdAndCategoryName(
                    Long.parseLong(request.getParameter("id")),
                    request.getParameter("category"))
            );
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid id, please specify correct number of id");
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("There is no such id, please specify correct number of id");
        }
    }

//    public void create(HttpServletRequest request, HttpServletResponse response) {
//        String name = request.getParameter("productName");
//        String description = request.getParameter("productDescription");
//        String categoryName = request.getParameter("productCategoryName");
//        Product requestedProduct = new Product(null, name, description, categoryName);
//        request.setAttribute("object", productService.create(requestedProduct));
//    }
//
//    public void delete(HttpServletRequest request, HttpServletResponse response) {
//        String name = request.getParameter("productName");
//        String description = request.getParameter("productDescription");
//        String categoryName = request.getParameter("productCategoryName");
//        Product requestedProduct = new Product(null, name, description, categoryName);
//        request.setAttribute("object", productService.delete(requestedProduct));
//    }
//
//    public void update(HttpServletRequest request, HttpServletResponse response) {
//        String name = request.getParameter("productName");
//        String description = request.getParameter("productDescription");
//        String categoryName = request.getParameter("productCategoryName");
//        Product requestedProduct = new Product(null, name, description, categoryName);
//        request.setAttribute("object", productService.update(requestedProduct));
//    }
}
