package com.odmytrenko.controller;

import com.odmytrenko.model.Roles;
import com.odmytrenko.model.User;
import com.odmytrenko.service.UserService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class UserEditController implements Controller {

    private final UserService userService;

    public UserEditController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        return new ViewModel("manageUser").
                addAttribute("userName", request.getParameter("userName")).
                addAttribute("password", request.getParameter("password")).
                addAttribute("roles", userService.getUser(new User(request.
                        getParameter("userName"), request.getParameter("password"))).getRoles()).
                addAttribute("allRoles", Roles.values());
    }
}
