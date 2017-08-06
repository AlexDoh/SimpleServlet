package com.odmytrenko.controller;

import com.odmytrenko.model.User;
import com.odmytrenko.service.UserService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class UserManipulationController implements Controller {

    private UserService userService;

    public UserManipulationController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");

        return CrudController.process(request, userService, new User(userName, password));
    }
}
