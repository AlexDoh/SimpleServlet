package com.odmytrenko.dao;

import com.odmytrenko.model.User;

public interface UserDao extends GenericDao<User> {

    User getUser(User user);

    User findByToken(String token);
}
