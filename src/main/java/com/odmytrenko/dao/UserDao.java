package com.odmytrenko.dao;

import com.odmytrenko.model.User;

import java.util.Set;

public interface UserDao extends GenericDao<User> {

    User getUser(User user);

    User findByToken(String token);

    Set<User> getAll();
}
