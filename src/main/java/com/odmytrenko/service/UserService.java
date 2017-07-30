package com.odmytrenko.service;

import com.odmytrenko.model.User;

public interface UserService {

    User getUser(User user);

    User create(User user);

    User delete(User user);

    User update(User user);

}
