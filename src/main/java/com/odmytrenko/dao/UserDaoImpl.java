package com.odmytrenko.dao;

import com.odmytrenko.factory.Factory;
import com.odmytrenko.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    static {
        try {
            String createTable = "CREATE TABLE IF NOT EXISTS USERS (\n" +
                    "  ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "  USERNAME VARCHAR(30),\n" +
                    "  TOKEN VARCHAR(255),\n" +
                    "  PASSWORD VARCHAR(255),\n" +
                    "  EMAIL VARCHAR(30),\n" +
                    "  ADMIN BOOLEAN\n" +
                    ");";
            Factory.getConnection().prepareStatement(createTable).execute();
            String user1 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ADMIN, ID) VALUES(" +
                    "'Vova', '123123', 'player1@gmail.com', 'token1', TRUE, 1);";
            String user2 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ADMIN, ID) VALUES(" +
                    "'Vova2', '123123', 'player2@gmail.com', 'token2', FALSE, 2);";
            String user3 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ADMIN, ID) VALUES(" +
                    "'Anton', '123123', 'player3@gmail.com', 'token3', FALSE, 3);";
            Factory.getConnection().prepareStatement(user1).execute();
            Factory.getConnection().prepareStatement(user2).execute();
            Factory.getConnection().prepareStatement(user3).execute();
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during query");
        }
    }

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public User getUser(User user) {
        String getUserQuery = "SELECT USERNAME, PASSWORD, EMAIL, TOKEN, ADMIN FROM USERS WHERE USERNAME = ? AND PASSWORD = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUserQuery);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User resultUser = new User(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
            resultUser.setEmail(resultSet.getString(3));
            resultUser.setToken(resultSet.getString(4));
            resultUser.setAdmin(Boolean.parseBoolean(resultSet.getString(5)));
            preparedStatement.close();
            return resultUser;
        } catch (SQLException e) {
            throw new RuntimeException("There is no such user");
        }
    }

    @Override
    public User findByToken(String token) {
        String findUserByToken = "SELECT USERNAME, PASSWORD, EMAIL, TOKEN, ADMIN FROM USERS WHERE TOKEN = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findUserByToken);

            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User resultUser = new User(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
            resultUser.setEmail(resultSet.getString(3));
            resultUser.setToken(resultSet.getString(4));
            resultUser.setAdmin(Boolean.parseBoolean(resultSet.getString(5)));
            preparedStatement.close();
            return resultUser;
        } catch (SQLException e) {
            throw new RuntimeException("There is no such user");
        }
    }

    @Override
    public User create(User user) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "INSERT INTO USERS (USERNAME, TOKEN, PASSWORD, EMAIL, ADMIN) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getToken());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, String.valueOf(user.isAdmin()));
            preparedStatement.execute();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during query");
        }
    }

    @Override
    public User delete(User user) {
        String deleteUser = "DELETE FROM USERS WHERE USERNAME = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUser);

            preparedStatement.setString(1, user.getName());
            preparedStatement.execute();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("There is no such user");
        }
    }

    @Override
    public User update(User user) {
        String findUserByToken = "UPDATE USERS SET " +
                "PASSWORD = ?," +
                "EMAIL = ?," +
                "ADMIN = ?" +
                "WHERE USERNAME = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findUserByToken);

            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, String.valueOf(user.isAdmin()));
            preparedStatement.setString(4, user.getName());
            preparedStatement.execute();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("There is no such user");
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
