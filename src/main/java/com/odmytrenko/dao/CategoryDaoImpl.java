package com.odmytrenko.dao;

import com.odmytrenko.factory.Factory;
import com.odmytrenko.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    static {
        try {
            String createCategoriesTable = "CREATE TABLE IF NOT EXISTS CATEGORIES (" +
                    " ID INT PRIMARY KEY AUTO_INCREMENT," +
                    " NAME VARCHAR(30)" +
                    ");";
            Factory.getConnection().prepareStatement(createCategoriesTable).execute();

            String category1 = "MERGE INTO CATEGORIES (NAME, ID) VALUES('Shoes', 1);";
            String category2 = "MERGE INTO CATEGORIES (NAME, ID) VALUES('Dresses', 2);";
            String category3 = "MERGE INTO CATEGORIES (NAME, ID) VALUES('Pants', 3);";

            Factory.getConnection().prepareStatement(category1).execute();
            Factory.getConnection().prepareStatement(category2).execute();
            Factory.getConnection().prepareStatement(category3).execute();
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query");
        }
    }

    public CategoryDaoImpl(Connection connection) {
        super(connection);
    }


    @Override
    public Category create(Category category) {
        if (doesCategoryExist(category.getName())) {
            throw new RuntimeException("Such category is already exist");
        }
        String createCategoryQuery = "INSERT INTO CATEGORIES (NAME) VALUES(?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createCategoryQuery)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.execute();
            return category;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    @Override
    public Category delete(Category category) {
        if (!doesCategoryExist(category.getName())) {
            throw new RuntimeException("There is no such category");
        }
        String deleteProductsInCategoryQuery = "DELETE FROM PRODUCTS WHERE CATEGORYID = (SELECT ID FROM CATEGORIES WHERE NAME = ?);";
        String deleteCategoryQuery = "DELETE FROM CATEGORIES WHERE NAME = ?;";
        try (PreparedStatement preparedStatementForCategory = connection.prepareStatement(deleteCategoryQuery);
             PreparedStatement preparedStatementForProducts = connection.prepareStatement(deleteProductsInCategoryQuery)) {
            preparedStatementForProducts.setString(1, category.getName());
            preparedStatementForCategory.setString(1, category.getName());
            preparedStatementForProducts.execute();
            preparedStatementForCategory.execute();
            return category;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    @Override
    public Category update(Category category) {
        throw new RuntimeException("Update category through product update procedure");
    }

    @Override
    public Category findById(Long id) {
        String findById = "SELECT NAME FROM CATEGORIES WHERE ID = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(findById)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            Category category = new Category(rs.getString("NAME"));
            category.setId(id);
            return category;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    @Override
    public Set<Category> getAll() {
        Set<Category> categories = new HashSet<>();
        String getAll = "SELECT ID, NAME FROM CATEGORIES;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAll)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getString("NAME"));
                category.setId(rs.getLong("ID"));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    private boolean doesCategoryExist(String categoryName) {
        String preparedQuery = "SELECT * FROM CATEGORIES WHERE NAME = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setString(1, categoryName);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("There are problems with finding category" + e);
        }
    }
}
