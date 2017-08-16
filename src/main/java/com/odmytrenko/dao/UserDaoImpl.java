package com.odmytrenko.dao;

import com.odmytrenko.factory.Factory;
import com.odmytrenko.model.Roles;
import com.odmytrenko.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    static {
        try {
            String createUsersTable = "CREATE TABLE IF NOT EXISTS USERS (" +
                    "   ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "   USERNAME VARCHAR(30)," +
                    "   TOKEN VARCHAR(255)," +
                    "   PASSWORD VARCHAR(255)," +
                    "   EMAIL VARCHAR(30)," +
                    ");";
            String createRolesTable = "CREATE TABLE IF NOT EXISTS ROLES (" +
                    "   ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "   NAME VARCHAR(30)" +
                    ");";
            String createUserToRoleTable = "CREATE TABLE IF NOT EXISTS USERTOROLE (" +
                    "   ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "   USERID INT," +
                    "   ROLEID INT," +
                    "   FOREIGN KEY (ROLEID) REFERENCES ROLES(ID)," +
                    "   FOREIGN KEY (USERID) REFERENCES USERS(ID)" +
                    ");";
            Factory.getConnection().prepareStatement(createUsersTable).execute();
            Factory.getConnection().prepareStatement(createRolesTable).execute();
            Factory.getConnection().prepareStatement(createUserToRoleTable).execute();

            String user1 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                    "'User1', '123123', 'player1@gmail.com', 'token1', 1);";
            String user2 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                    "'User2', '123123', 'player2@gmail.com', 'token2', 2);";
            String user3 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                    "'User3', '123123', 'player3@gmail.com', 'token3', 3);";
            String roles = "MERGE INTO ROLES (NAME, ID) VALUES('ADMIN', 1);" +
                    "MERGE INTO ROLES (NAME, ID) VALUES('USER', 2);" +
                    "MERGE INTO ROLES (NAME, ID) VALUES('MODERATOR', 3);";
            String roleAssignments = "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(1, 1, 1);" +
                    "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(1, 2, 2);" +
                    "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(2, 2, 3);" +
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
        String preparedQuery = "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME FROM USERS AS U" +
                " JOIN USERTOROLE AS UR ON UR.USERID=U.ID" +
                " JOIN ROLES AS R ON R.ID = UR.ROLEID" +
                " WHERE U.USERNAME = ? AND U.PASSWORD = ?;";
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
        String preparedQuery = "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME" +
                " FROM USERS U" +
                " JOIN USERTOROLE UR ON UR.USERID=U.ID" +
                " JOIN ROLES R ON R.ID = UR.ROLEID" +
                " WHERE U.TOKEN = ?;";
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
        String deleteUserRoleQuery = "DELETE FROM USERTOROLE WHERE USERID = (SELECT ID FROM USERS WHERE USERNAME = ?);";
        String deleteUserQuery = "DELETE FROM USERS WHERE USERNAME = ?;";

        try (PreparedStatement preparedStatementForRole = connection.prepareStatement(deleteUserRoleQuery);
             PreparedStatement preparedStatementForUser = connection.prepareStatement(deleteUserQuery)) {

            preparedStatementForRole.setString(1, user.getName());
            preparedStatementForUser.setString(1, user.getName());
            preparedStatementForRole.execute();
            preparedStatementForUser.execute();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("There is no such user");
        }
    }

    @Override
    public User update(User user) {
        String updateUserQuery = "UPDATE USERS SET PASSWORD = ?, EMAIL = ? WHERE USERNAME = ?;";
        String deleteUserRoleQuery = "DELETE FROM USERTOROLE WHERE USERID = (SELECT ID FROM USERS WHERE USERNAME = ?);";
        String insertUserRoleQuery = "INSERT INTO USERTOROLE (USERID, ROLEID) VALUES((SELECT ID FROM USERS WHERE USERNAME = ?), (SELECT ID FROM ROLES WHERE NAME = ?));";

        try (PreparedStatement preparedStatementForUser = connection.prepareStatement(updateUserQuery);
             PreparedStatement preparedStatementForDeleteRole = connection.prepareStatement(deleteUserRoleQuery);
             PreparedStatement preparedStatementForUpdateRole = connection.prepareStatement(insertUserRoleQuery)) {

            preparedStatementForUser.setString(1, user.getPassword());
            preparedStatementForUser.setString(2, user.getEmail());
            preparedStatementForUser.setString(3, user.getName());
            preparedStatementForDeleteRole.setString(1, user.getName());
            preparedStatementForUser.execute();
            preparedStatementForDeleteRole.execute();

            user.getRoles().stream().map(Enum::toString).forEach(c -> {
                try {
                    preparedStatementForUpdateRole.setString(1, user.getName());
                    preparedStatementForUpdateRole.setString(2, c);
                    preparedStatementForUpdateRole.execute();
                } catch (SQLException ignored) {
                }
            });
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("There is no such user");
        }
    }

    @Override
    public Set<User> getAll() {
        String preparedQuery = "SELECT U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME FROM USERS AS U" +
                " JOIN USERTOROLE AS UR ON UR.USERID=U.ID" +
                " JOIN ROLES AS R ON R.ID = UR.ROLEID;";
        Set<User> resultSet = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"));
                if (resultSet.contains(user)) {
                    resultSet.stream().filter(u -> u.equals(user)).findFirst().get().getRoles().add(Roles.valueOf(rs.
                            getString("NAME")));
                    resultSet.add(user);
                } else {
                    user.setId(rs.getLong("ID"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setToken(rs.getString("TOKEN"));
                    user.getRoles().add(Roles.valueOf(rs.getString("NAME")));
                    resultSet.add(user);
                }
            }
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException("There are problems with getting users" + e);
        }
    }

    private Set<Roles> getUserRolesFromQuery(ResultSet rs) throws SQLException {
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.valueOf(rs.getString("NAME")));
        try {
            while (rs.next()) {
                roles.add(Roles.valueOf(rs.getString("NAME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("There are problems with getting users" + e);
        }
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
