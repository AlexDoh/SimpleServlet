package com.odmytrenko.service;

import com.odmytrenko.dao.UserDao;
import com.odmytrenko.model.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(User user) {
        checkUser(user);
        return userDao.getUser(user);
    }

    @Override
    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    private void checkUser(User user) {
        if (userDao.getUser(user) == null) {
            throw new RuntimeException("User " + user + " does not exist");
        }
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }
}