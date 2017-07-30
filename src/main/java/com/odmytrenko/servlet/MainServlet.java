package com.odmytrenko.servlet;

import com.odmytrenko.controller.*;
import com.odmytrenko.factory.Factory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private UserController userController;
    private GetAllCategoriesController getAllCategoriesController;
    private GetAllProductsController getAllProductsController;
    private CategoryController categoryController;
    private ProductController productController;

    private String index = null;
    private String login = null;
    private String welcome = null;
    private String categories = null;
    private String products = null;
    private String categorySearch = null;
    private String productSearch = null;
    private String adminConsole = null;
    private String adminAction = null;
    private MainServletManagement management;

    @Override
    public void init() throws ServletException {
        ServletConfig servletConfig = this.getServletConfig();
        userController = Factory.getUserController();
        getAllCategoriesController = Factory.getAllCategoriesController();
        getAllProductsController = Factory.getAllProductsController();
        categoryController = Factory.getCategoryController();
        productController = Factory.getProductController();
        this.index = servletConfig.getInitParameter("index");
        this.login = servletConfig.getInitParameter("login");
        this.welcome = servletConfig.getInitParameter("welcome");
        this.categories = servletConfig.getInitParameter("categories");
        this.products = servletConfig.getInitParameter("products");
        this.categorySearch = servletConfig.getInitParameter("categorySearch");
        this.productSearch = servletConfig.getInitParameter("productSearch");
        this.adminConsole = servletConfig.getInitParameter("adminConsole");
        this.adminAction = servletConfig.getInitParameter("adminAction");
        management = new MainServletManagement(new File(this.getServletContext().
                getRealPath("WEB-INF/views/")).list());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getRequestURI().equals(management.getIndexJSP())) {
            request.getRequestDispatcher(index).forward(request, response);
        }

        if (request.getRequestURI().equals(management.getLoginJSP())) {
            request.getRequestDispatcher(login).forward(request, response);
        }

        if (request.getRequestURI().equals(management.getCategoriesJSP())) {
            getAllCategoriesController.getAllCategories(request, response);
            request.getRequestDispatcher(categories).forward(request, response);
        }

        if (request.getRequestURI().equals(management.getProductsJSP())) {
            getAllProductsController.getAllProducts(request, response, request.getParameter(management.getParametrCategory()));
            request.getRequestDispatcher(products).forward(request, response);
        }

        if (request.getRequestURI().equals(management.getAdminJSP())) {
            request.getRequestDispatcher(adminConsole).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(management.getLoginJSP())) {
            userController.getUser(request, response);
            request.getRequestDispatcher(welcome).forward(request, response);
        }

        if (request.getRequestURI().equals(management.getCategoriesJSP())) {
            try {
                categoryController.findById(request, response, Long.parseLong(request.getParameter(management.getParametrFindById())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher(categorySearch).forward(request, response);
        }

        if (request.getRequestURI().equals(management.getProductsJSP())) {
            try {
                productController.findByIdAndCategoryName(request, response, Long.parseLong(request.getParameter
                        (management.getParametrFindById())), request.getParameter(management.getParametrCategory()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher(productSearch).forward(request, response);
        }

        if (request.getRequestURI().equals(management.getAdminJSP())) {
            if (request.getParameterMap().containsKey(management.getParametrUserAction())) {
                switch (request.getParameter(management.getParametrUserAction())) {
                    case "add":
                        userController.create(request, response);
                        break;
                    case "delete":
                        userController.delete(request, response);
                        break;
                    case "update":
                        userController.update(request, response);
                        break;
                }
            }
            if (request.getParameterMap().containsKey(management.getParametrCategoryAction())) {
                switch (request.getParameter(management.getParametrCategoryAction())) {
                    case "add":
                        categoryController.create(request, response);
                        break;
                    case "delete":
                        categoryController.delete(request, response);
                        break;
                    case "update":
                        categoryController.update(request, response);
                        break;
                }
            }
            if (request.getParameterMap().containsKey(management.getParametrProductAction())) {
                switch (request.getParameter(management.getParametrProductAction())) {
                    case "add":
                        productController.create(request, response);
                        break;
                    case "delete":
                        productController.delete(request, response);
                        break;
                    case "update":
                        productController.update(request, response);
                        break;
                }
            }
            request.getRequestDispatcher(adminAction).forward(request, response);
        }
    }
}