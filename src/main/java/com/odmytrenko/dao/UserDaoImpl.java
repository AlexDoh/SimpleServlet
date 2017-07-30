package com.odmytrenko.dao;

import com.odmytrenko.model.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Set<User> userList = new HashSet<>();

    static {
        userList.add(new User("Vova", "123123"));
        userList.add(new User("Vova2", "123123"));
        userList.add(new User("Anton", "123123"));
    }

    public User getUser(User user) {
        userList.forEach(i -> System.out.println(i.getName()));
        if (userList.contains(user)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User create(User user) {
        userList.add(user);
        userList.forEach(i -> System.out.println(i.getName()));
        return user;
    }

    @Override
    public User delete(User user) {
        userList.remove(user);
        userList.forEach(i -> System.out.println(i.getName()));
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
