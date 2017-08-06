package com.odmytrenko.dao;

import com.odmytrenko.model.User;

import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Set<User> userList = new HashSet<>();

    static {
        User user1 = new User("Vova", "123123");
        user1.setToken("token1");
        user1.setAdmin(true);
        User user2 = new User("Vova2", "123123");
        user2.setToken("token2");
        User user3 = new User("Anton", "123123");
        user3.setToken("token3");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }

    public User getUser(User user) {
        if (userList.stream().anyMatch(p -> p.equals(user))) {
            return userList.stream().filter(p -> p.equals(user)).findFirst().get();
        }
        return null;
    }

    @Override
    public User findByToken(String token) {
        if (userList.stream().anyMatch(p -> p.getToken().equals(token))) {
            return userList.stream().filter(p -> p.getToken().equals(token)).findFirst().get();
        }
        return null;
    }

    @Override
    public User create(User user) {
        userList.add(user);
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
}
