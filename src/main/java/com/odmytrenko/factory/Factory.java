package com.odmytrenko.factory;

import com.odmytrenko.controller.*;
import com.odmytrenko.dao.ProductDao;
import com.odmytrenko.dao.ProductDaoImpl;
import com.odmytrenko.dao.CategoryDao;
import com.odmytrenko.dao.CategoryDaoImpl;
import com.odmytrenko.dao.UserDao;
import com.odmytrenko.dao.UserDaoImpl;
import com.odmytrenko.service.ProductService;
import com.odmytrenko.service.ProductServiceImpl;
import com.odmytrenko.service.CategoryService;
import com.odmytrenko.service.CategoryServiceImpl;
import com.odmytrenko.service.UserService;
import com.odmytrenko.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

    public static Controller getUserManagement() {
        return new UserManagementController(Factory.getUserService());
    }

    public static Controller getImageUploadController() {
        return new UserImageUploadController();
    }

    public static Controller getRegistrationController() {
        return new RegistrationController();
    }

    public static UserService getUserService() {
        return new UserServiceImpl(Factory.getUserDao());
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl(getConnection());
    }

    public static Controller getAllCategoriesController() {
        return new GetAllCategoriesController(Factory.getCategoryService());
    }

    private static CategoryService getCategoryService() {
        return new CategoryServiceImpl(Factory.getCategoryDao());
    }

    private static CategoryDao getCategoryDao() {
        return new CategoryDaoImpl(getConnection());
    }

    public static Controller getAllProductsController() {
        return new GetAllProductsController(Factory.getProductService());
    }

    private static ProductService getProductService() {
        return new ProductServiceImpl(Factory.getProductDao());
    }

    private static ProductDao getProductDao() {
        return new ProductDaoImpl(getConnection());
    }

    public static Controller getCategoryController() {
        return new CategoryController(Factory.getCategoryService());
    }

    public static Controller getProductController() {
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
        return new AdminController(Factory.getCategoryService());
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

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Driver was not initialized");
        }
        return connection;
    }
}
