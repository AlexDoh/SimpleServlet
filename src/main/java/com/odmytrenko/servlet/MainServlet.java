package com.odmytrenko.servlet;

import com.odmytrenko.controller.*;
import com.odmytrenko.factory.Factory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    private Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put(Request.create("GET", "/filter/"), Factory.getIndexController());
        controllerMap.put(Request.create("GET", "/filter/login"), Factory.getLoginController());
        controllerMap.put(Request.create("GET", "/filter/profile"), Factory.getLoginController());
        controllerMap.put(Request.create("POST", "/filter/profile"), Factory.getProfileController());
        controllerMap.put(Request.create("GET", "/filter/categories"), Factory.getAllCategoriesController());
        controllerMap.put(Request.create("GET", "/filter/products"), Factory.getAllProductsController());
        controllerMap.put(Request.create("GET", "/filter/category"), Factory.getCategoryController());
        controllerMap.put(Request.create("POST", "/filter/category"), Factory.getCategoryController());
        controllerMap.put(Request.create("GET", "/filter/product"), Factory.getProductController());
        controllerMap.put(Request.create("POST", "/filter/product"), Factory.getProductController());
        controllerMap.put(Request.create("GET", "/filter/adminconsole"), Factory.getAdminController());
        controllerMap.put(Request.create("POST", "/filter/performedadminaction"), Factory.getManipulationController());
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
                    Cookie cookie = new Cookie(cookieName, newCookie.get(cookieName));
                    cookie.setMaxAge(3600 * 24 * 30);
                    cookie.setPath("/");
                    response.addCookie(cookie);
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