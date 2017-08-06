package com.odmytrenko.controller;

import com.odmytrenko.factory.Factory;
import com.odmytrenko.servlet.Request;
import com.odmytrenko.servlet.ViewModel;

import java.lang.reflect.InvocationTargetException;

public class ManipulationController implements Controller {

    @Override
    public ViewModel process(Request request) {
        try {
            Controller controller = (Controller) Factory.class.getMethod("get" + request.getParameter("type") +
                    "ManipulationController").invoke(Factory.class);
            return controller.process(request);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e.getClass() + " " + e.getCause() + ' ' + e.getMessage());
        }
    }
}
