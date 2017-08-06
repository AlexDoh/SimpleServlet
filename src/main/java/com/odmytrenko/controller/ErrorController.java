package com.odmytrenko.controller;

import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class ErrorController implements Controller {
    @Override
    public ViewModel process(Request request) {
        return new ViewModel("error").addAttribute("error", request.getParameter("error"));
    }
}
