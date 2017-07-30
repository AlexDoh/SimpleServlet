package com.odmytrenko.controller;

import com.odmytrenko.model.User;
import com.odmytrenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User requestedUser = new User(userName, password);
        request.setAttribute("user", userService.getUser(requestedUser));
    }

    public void create(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User requestedUser = new User(userName, password);
        request.setAttribute("object", userService.create(requestedUser));
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User requestedUser = new User(userName, password);
        request.setAttribute("object", userService.delete(requestedUser));
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User requestedUser = new User(userName, password);
        request.setAttribute("object", userService.update(requestedUser));
    }

}
