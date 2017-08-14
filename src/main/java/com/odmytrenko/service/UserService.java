package com.odmytrenko.service;

import com.odmytrenko.model.User;

import java.util.Set;

public interface UserService extends CrudService<User> {

    User getUser(User user);

    User create(User user);

    User delete(User user);

    User update(User user);

    User findByToken(String token);

    Set<User> getAll();

}
