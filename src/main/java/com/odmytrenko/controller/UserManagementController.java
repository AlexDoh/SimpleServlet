package com.odmytrenko.controller;

import com.odmytrenko.service.UserService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class UserManagementController implements Controller {

    private UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        return new ViewModel("userManagement").addAttribute("users", userService.getAll());
    }
}
