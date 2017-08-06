package com.odmytrenko.dao;

import com.odmytrenko.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Set<User> userList = new HashSet<>();

    static {
        User user1 = new User("Vova", "123123");
        user1.setEmail("player1@gmail.com");
        user1.setToken("token1");
        user1.setAdmin(true);
        User user2 = new User("Vova2", "123123");
        user1.setEmail("player2@gmail.com");
        user2.setToken("token2");
        User user3 = new User("Anton", "123123");
        user1.setEmail("player3@gmail.com");
        user3.setToken("token3");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
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
        String getUserQuery = "SELECT USERNAME, PASSWORD, EMAIL, TOKEN, ADMIN FROM USERS WHERE TOKEN = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUserQuery);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User delete(User user) {
        userList.remove(user);
        return user;
    }

    @Override
    public User update(User user) {
        userList.removeIf(i -> i.getName().equals(user.getName()));
        userList.add(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

//    @Override
//    public User create(User user) {
//        userList.add(user);
//        return user;
//    }
//
//    @Override
//    public User delete(User user) {
//        userList.remove(user);
//        return user;
//    }
//
//    @Override
//    public User update(User user) {
//        userList.removeIf(i -> i.getName().equals(user.getName()));
//        userList.add(user);
//        return user;
//    }
//
//    @Override
//    public User findById(Long id) {
//        return null;
//    }
}
