package com.odmytrenko.controller;

import com.odmytrenko.model.Model;
import com.odmytrenko.service.CrudService;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

public class CrudController {

    public static ViewModel process(Request request, CrudService service, Model model) {
        switch (request.getParameter("action")) {
            case "add":
                return new ViewModel("performedAction").
                        addAttribute("object", service.create(model));
            case "update":
                return new ViewModel("performedAction").
                        addAttribute("object", service.update(model));
            case "delete":
                return new ViewModel("performedAction").
                        addAttribute("object", service.delete(model));
        }
        throw new RuntimeException("There was problem with the operation");
    }
}
