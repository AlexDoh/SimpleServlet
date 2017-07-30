package com.odmytrenko.factory;

import com.odmytrenko.controller.*;
import com.odmytrenko.dao.*;
import com.odmytrenko.service.*;

public class Factory {

    public static UserController getUserController() {
        return new UserController(Factory.getUserService());
    }

    private static UserService getUserService() {
        return new UserServiceImpl(Factory.getUserDao());
    }

    private static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static GetAllCategoriesController getAllCategoriesController() {
        return new GetAllCategoriesController(Factory.getCategoryService());
    }

    private static CategoryService getCategoryService() {
        return new CategoryServiceImpl(Factory.getCategoryDao());
    }

    private static CategoryDao getCategoryDao() {
        return new CategoryDaoImpl();
    }

    public static GetAllProductsController getAllProductsController() {
        return new GetAllProductsController(Factory.getProductService());
    }

    private static ProductService getProductService() {
        return new ProductServiceImpl(Factory.getProductDao());
    }

    private static ProductDao getProductDao() {
        return new ProductDaoImpl();
    }

    public static CategoryController getCategoryController() {
        return new CategoryController(Factory.getCategoryService());
    }

    public static ProductController getProductController() {
        return new ProductController(Factory.getProductService());
    }
}
