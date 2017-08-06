package com.odmytrenko.controller;

import com.odmytrenko.model.User;
import com.odmytrenko.service.UserService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class ProfileController implements Controller {

    private final UserService userService;

    public ProfileController(UserService service) {
        this.userService = service;
    }

    @Override
    public ViewModel process(Request request) {

        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User user = userService.getUser(new User(userName, password));
        return new ViewModel("profile").addCookie("token", user.getToken()).addAttribute("user", user);
    }
}
