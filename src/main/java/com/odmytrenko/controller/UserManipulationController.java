package com.odmytrenko.controller;

import com.odmytrenko.model.Roles;
import com.odmytrenko.model.User;
import com.odmytrenko.service.UserService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserManipulationController implements Controller {

    private UserService userService;

    public UserManipulationController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        String userName = request.getParameter("userName");
        String password;
        if (request.hasParameter("userPassword")) {
            password = request.getParameter("userPassword");
        } else {
            password = null;
        }
        User user = new User(userName, password);
        if (request.hasParameter("userEmail")) {
            user.setEmail(request.getParameter("userEmail"));
            user.setToken(userName + System.nanoTime());
        }
        if(request.hasParameter("roles")) {
            user.setRoles(Arrays.stream(request.getParametersArray("roles")).map(Roles::valueOf).
                    collect(Collectors.toSet()));
        } else {
            Set<Roles> roles = new HashSet<>();
            roles.add(Roles.USER);
            user.setRoles(roles);
        }
        return CrudController.process(request, userService, user);
    }
}
