package com.odmytrenko.dao;

import com.odmytrenko.factory.Factory;
import com.odmytrenko.model.Category;
import com.odmytrenko.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {

    private final CategoryDao categoryDao;

    static {
        try {
            String createProductsTable = "CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                    " ID INT PRIMARY KEY AUTO_INCREMENT," +
                    " NAME VARCHAR(30)," +
                    " DESCRIPTION VARCHAR(255)," +
                    " CATEGORYID INT," +
                    " FOREIGN KEY (CATEGORYID) REFERENCES CATEGORIES(ID)" +
                    ");";
            Factory.getConnection().prepareStatement(createProductsTable).execute();

            String product1 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Low shoe'," +
                    " 'HighQualifiedProduct', 1, 1);";
            String product2 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Sandals'," +
                    " 'MiddleQualifiedProduct', 1, 2);";
            String product3 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Boots'," +
                    " 'LowQualifiedProduct', 1, 3);";
            String product4 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Shift'," +
                    " 'HighQualifiedProduct', 2, 4);";
            String product5 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Doll'," +
                    " 'MiddleQualifiedProduct', 2, 5);";
            String product6 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Kimono'," +
                    " 'LowQualifiedProduct', 2, 6);";
            String product7 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Breeches'," +
                    " 'HighQualifiedProduct', 3, 7);";
            String product8 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Trousers'," +
                    " 'MiddleQualifiedProduct', 3, 8);";
            String product9 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Drawers'," +
                    " 'LowQualifiedProduct', 3, 9);";

            Factory.getConnection().prepareStatement(product1).execute();
            Factory.getConnection().prepareStatement(product2).execute();
            Factory.getConnection().prepareStatement(product3).execute();
            Factory.getConnection().prepareStatement(product4).execute();
            Factory.getConnection().prepareStatement(product5).execute();
            Factory.getConnection().prepareStatement(product6).execute();
            Factory.getConnection().prepareStatement(product7).execute();
            Factory.getConnection().prepareStatement(product8).execute();
            Factory.getConnection().prepareStatement(product9).execute();
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query");
        }
    }

    public ProductDaoImpl(Connection connection, CategoryDao categoryDao) {
        super(connection);
        this.categoryDao = categoryDao;
    }

    @Override
    public Set<Product> getAllByCategoryId(Long categoryId) {
        Set<Product> products = new HashSet<>();
        String getAll = "SELECT ID, NAME, DESCRIPTION FROM PRODUCTS WHERE CATEGORYID = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAll)) {
            preparedStatement.setLong(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Category category = categoryDao.findById(categoryId);
                Product product = new Product(rs.getString("NAME"), rs.getString("DESCRIPTION"),
                        category);
                product.setId(rs.getLong("ID"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    @Override
    public Product findById(Long id) {
        String findById = "SELECT CATEGORYID, NAME, DESCRIPTION FROM PRODUCTS WHERE ID = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(findById)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            Category category = categoryDao.findById(rs.getLong("CATEGORYID"));
            Product product = new Product(rs.getString("NAME"), rs.getString("DESCRIPTION"),
                    category);
            product.setId(id);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    @Override
    public Set<Product> getAll() {
        Set<Product> products = new HashSet<>();
        String getAll = "SELECT ID, CATEGORYID, NAME, DESCRIPTION FROM PRODUCTS;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAll)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Category category = categoryDao.findById(rs.getLong("CATEGORYID"));
                Product product = new Product(rs.getString("NAME"), rs.getString("DESCRIPTION"),
                        category);
                product.setId(rs.getLong("ID"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    @Override
    public Product create(Product product) {
        String createQuery = "INSERT INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID) VALUES(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setLong(3, product.getCategory().getId());
            preparedStatement.execute();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    @Override
    public Product delete(Product product) {
        String deleteQuery = "DELETE FROM PRODUCTS WHERE NAME = ? AND CATEGORYID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getCategory().getId());
            preparedStatement.execute();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }

    @Override
    public Product update(Product product) {
        String updateQuery = "UPDATE PRODUCTS SET DESCRIPTION = ?, CATEGORYID = ? WHERE NAME = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setLong(2, product.getCategory().getId());
            preparedStatement.setString(3, product.getName());
            preparedStatement.execute();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query " + e);
        }
    }
}
