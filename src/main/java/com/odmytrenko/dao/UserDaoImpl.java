package com.odmytrenko.dao;

import com.odmytrenko.factory.Factory;
import com.odmytrenko.model.Roles;
import com.odmytrenko.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    static {
        try {
            String createUsersTable = "CREATE TABLE IF NOT EXISTS USERS (\n" +
                    "   ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "   USERNAME VARCHAR(30),\n" +
                    "   TOKEN VARCHAR(255),\n" +
                    "   PASSWORD VARCHAR(255),\n" +
                    "   EMAIL VARCHAR(30),\n" +
                    ");";
            String createRolesTable = "CREATE TABLE IF NOT EXISTS ROLES (\n" +
                    "   ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "   NAME VARCHAR(30)\n" +
                    ");";
            String createUserToRoleTable = "CREATE TABLE IF NOT EXISTS USERTOROLE (\n" +
                    "   ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "   USERID INT,\n" +
                    "   ROLEID INT,\n" +
                    "   FOREIGN KEY (ROLEID) REFERENCES ROLES(ID),\n" +
                    "   FOREIGN KEY (USERID) REFERENCES USERS(ID)\n" +
                    ");";
            Factory.getConnection().prepareStatement(createUsersTable).execute();
            Factory.getConnection().prepareStatement(createRolesTable).execute();
            Factory.getConnection().prepareStatement(createUserToRoleTable).execute();

            String user1 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                    "'Vova', '123123', 'player1@gmail.com', 'token1', 1);";
            String user2 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                    "'Vova2', '123123', 'player2@gmail.com', 'token2', 2);";
            String user3 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                    "'Anton', '123123', 'player3@gmail.com', 'token3', 3);";
            String roles = "MERGE INTO ROLES (NAME, ID) VALUES('ADMIN', 1);\n" +
                    "MERGE INTO ROLES (NAME, ID) VALUES('USER', 2);\n" +
                    "MERGE INTO ROLES (NAME, ID) VALUES('MODERATOR', 3);";
            String roleAssignments = "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(1, 1, 1);\n" +
                    "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(1, 2, 2);\n" +
                    "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(2, 2, 3);\n" +
                    "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(3, 2, 4);";
            Factory.getConnection().prepareStatement(user1).execute();
            Factory.getConnection().prepareStatement(user2).execute();
            Factory.getConnection().prepareStatement(user3).execute();
            Factory.getConnection().prepareStatement(roles).execute();
            Factory.getConnection().prepareStatement(roleAssignments).execute();
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query");
        }
    }

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public User getUser(User user) {
        String preparedQuery = "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME FROM USERS AS U\n" +
                "JOIN USERTOROLE AS UR ON UR.USERID=U.ID\n" +
                "JOIN ROLES AS R ON R.ID = UR.ROLEID\n" +
                "WHERE U.USERNAME = ? AND U.PASSWORD = ?;";
        Set<Roles> roles = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            User resultUser = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"));
            resultUser.setId(rs.getLong("ID"));
            resultUser.setEmail(rs.getString("EMAIL"));
            resultUser.setToken(rs.getString("TOKEN"));
            resultUser.setRoles(roles);
            roles.addAll(getUserRolesFromQuery(rs));
            return resultUser;
        } catch (SQLException e) {
            throw new RuntimeException("There are problems with authentication" + e);
        }
    }

    @Override
    public User findByToken(String token) {
        String preparedQuery = "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME " +
                "FROM USERS U " +
                "JOIN USERTOROLE UR ON UR.USERID=U.ID " +
                "JOIN ROLES R ON R.ID = UR.ROLEID " +
                "WHERE U.TOKEN = ?;";
        Set<Roles> roles = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setString(1, token);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            User resultUser = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"));
            resultUser.setId(rs.getLong("ID"));
            resultUser.setEmail(rs.getString("EMAIL"));
            resultUser.setToken(rs.getString("TOKEN"));
            resultUser.setRoles(roles);
            roles.addAll(getUserRolesFromQuery(rs));
            return resultUser;
        } catch (SQLException e) {
            throw new RuntimeException("There are problems with authentication" + e);
        }
    }

    @Override
    public User create(User user) {
        if (doesUserExist(user.getName())) {
            throw new RuntimeException("Such user in already exist");
        }
        String createUserQuery = "INSERT INTO USERS (USERNAME, TOKEN, PASSWORD, EMAIL) VALUES(?, ?, ?, ?);";
        String addRoleQuery = "INSERT INTO USERTOROLE (USERID, ROLEID) VALUES((SELECT ID FROM USERS WHERE USERNAME = ?), 2);";
        try (PreparedStatement preparedStatementForUser = connection.prepareStatement(createUserQuery);
             PreparedStatement preparedStatementForRole = connection.prepareStatement(addRoleQuery)) {
            preparedStatementForUser.setString(1, user.getName());
            preparedStatementForUser.setString(2, user.getToken());
            preparedStatementForUser.setString(3, user.getPassword());
            preparedStatementForUser.setString(4, user.getEmail());
            preparedStatementForUser.execute();
            preparedStatementForRole.setString(1, user.getName());
            preparedStatementForRole.execute();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error during the query");
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
            preparedStatement.setString(4, user.getName());
            preparedStatement.execute();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("There is no such user");
        }
    }

    private Set<Roles> getUserRolesFromQuery(ResultSet rs) throws SQLException {
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.valueOf(rs.getString("NAME")));
        roles.addAll(Stream.of(rs).map(f -> {
            try {
                if(f.next()) {
                    return Roles.valueOf(rs.getString("NAME"));
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException("There are problems with authentication" + e);
            }
        }).collect(Collectors.toSet()));
        return roles;
    }

    private boolean doesUserExist(String userName) {
        String preparedQuery = "SELECT * FROM USERS WHERE USERNAME = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("There are problems with authentication" + e);
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
