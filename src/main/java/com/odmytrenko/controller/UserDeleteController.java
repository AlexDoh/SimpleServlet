package com.odmytrenko.controller;

import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class UserDeleteController implements Controller {

    @Override
    public ViewModel process(Request request) {
        return new ViewModel("deleteUser").addAttribute("userName", request.getParameter("userName"));
    }
}
