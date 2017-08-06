package com.odmytrenko.factory;

import com.odmytrenko.controller.*;
import com.odmytrenko.dao.*;
import com.odmytrenko.service.*;

public class Factory {

    public static UserManipulationController getUserController() {
        return new UserManipulationController(Factory.getUserService());
    }

    public static UserService getUserService() {
        return new UserServiceImpl(Factory.getUserDao());
    }

    public static UserDao getUserDao() {
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

    public static Controller getIndexController() {
        return new IndexController();
    }

    public static Controller getLoginController() {
        return new LoginController();
    }

    public static Controller getProfileController() {
        return new ProfileController(Factory.getUserService());
    }

    public static Controller getAdminController() {
        return new AdminController();
    }

    public static Controller getManipulationController() {
        return new ManipulationController();
    }

    public static Controller getUserManipulationController() {
        return new UserManipulationController(Factory.getUserService());
    }

    public static Controller getCategoryManipulationController() {
        return new CategoryManipulationController(Factory.getCategoryService());
    }

    public static Controller getProductManipulationController() {
        return new ProductManipulationController(Factory.getProductService());
    }
}
