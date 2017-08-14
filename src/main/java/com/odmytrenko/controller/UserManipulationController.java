package com.odmytrenko.controller;

import com.odmytrenko.model.Roles;
import com.odmytrenko.model.User;
import com.odmytrenko.service.UserService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UserManipulationController implements Controller {

    private UserService userService;

    public UserManipulationController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User user = new User(userName, password);
        user.setEmail(request.getParameter("userEmail"));
        user.setToken(userName + System.nanoTime());
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.USER);
        user.setRoles(roles);
        return CrudController.process(request, userService, user);
    }
}
