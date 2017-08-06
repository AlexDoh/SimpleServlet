package com.odmytrenko.servlet;

import com.odmytrenko.controller.*;
import com.odmytrenko.factory.Factory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    private Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put(Request.create("GET", "/"), Factory.getIndexController());
        controllerMap.put(Request.create("GET", "/login"), Factory.getLoginController());
        controllerMap.put(Request.create("GET", "/profile"), Factory.getLoginController());
        controllerMap.put(Request.create("POST", "/profile"), Factory.getProfileController());
        controllerMap.put(Request.create("GET", "/categories"), Factory.getAllCategoriesController());
        controllerMap.put(Request.create("GET", "/products"), Factory.getAllProductsController());
        controllerMap.put(Request.create("GET", "/category"), Factory.getCategoryController());
        controllerMap.put(Request.create("POST", "/category"), Factory.getCategoryController());
        controllerMap.put(Request.create("GET", "/product"), Factory.getProductController());
        controllerMap.put(Request.create("POST", "/product"), Factory.getProductController());
        controllerMap.put(Request.create("GET", "/adminconsole"), Factory.getAdminController());
        controllerMap.put(Request.create("POST", "/performedadminaction"), Factory.getManipulationController());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Request req = new Request(request.getParameterMap(), request.getMethod(), request.getRequestURI());

        try {
            Controller controller = controllerMap.get(req);
            if (controller == null) {
                throw new RuntimeException("Cannot handle" + req.getUri());
            }

            ViewModel vm = controller.process(req);

            if (vm.hasCookie()) {
                Map<String, String> newCookie = vm.getNewCookie();
                for (String cookieName : newCookie.keySet()) {
                    response.addCookie(new Cookie(cookieName, newCookie.get(cookieName)));
                }
            }

            forward(request, response, vm);
        } catch (Throwable t) {
            ViewModel vm = new ErrorController().process(req);
            vm.addAttribute("error", t.getClass() + " " + t.getMessage() + ' ' + t.getMessage());
            forward(request, response, vm);
        }
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, ViewModel vm) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(getView(vm));
        setAttributes(req, vm);
        dispatcher.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req, ViewModel vm) {
        for (String attribute : vm.getAttributes().keySet()) {
            req.setAttribute(attribute, vm.getAttribute(attribute));
        }
    }

    private String getView(ViewModel vm) {
        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        return prefix + vm.getView() + suffix;
    }
}