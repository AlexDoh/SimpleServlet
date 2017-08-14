package com.odmytrenko.servlet;

import com.odmytrenko.controller.Controller;
import com.odmytrenko.controller.ErrorController;
import com.odmytrenko.factory.Factory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    private Map<Request, Controller> controllerMap = new HashMap<>();
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    @Override
    public void init() throws ServletException {
        controllerMap.put(Request.create("GET", "/filter/"), Factory.getIndexController());
        controllerMap.put(Request.create("GET", "/filter/login"), Factory.getLoginController());
        controllerMap.put(Request.create("GET", "/filter/registration"), Factory.getRegistrationController());
        controllerMap.put(Request.create("GET", "/filter/profile"), Factory.getLoginController());
        controllerMap.put(Request.create("POST", "/filter/profile"), Factory.getProfileController());
        controllerMap.put(Request.create("GET", "/filter/categories"), Factory.getAllCategoriesController());
        controllerMap.put(Request.create("GET", "/filter/products"), Factory.getAllProductsController());
        controllerMap.put(Request.create("GET", "/filter/category"), Factory.getCategoryController());
        controllerMap.put(Request.create("POST", "/filter/category"), Factory.getCategoryController());
        controllerMap.put(Request.create("GET", "/filter/product"), Factory.getProductController());
        controllerMap.put(Request.create("POST", "/filter/product"), Factory.getProductController());
        controllerMap.put(Request.create("GET", "/filter/adminconsole"), Factory.getAdminController());
        controllerMap.put(Request.create("POST", "/filter/performedaction"), Factory.getManipulationController());
        controllerMap.put(Request.create("POST", "/filter/profile/imageupload"), Factory.getImageUploadController());
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
        req.setParameter("file-upload", new String[]{getServletContext().getInitParameter("file-upload")});

        try {
            Controller controller = controllerMap.get(req);
            if (controller == null) {
                throw new RuntimeException("Cannot handle" + req.getUri());
            }

            if (ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(MAX_MEMORY_SIZE);
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(MAX_REQUEST_SIZE);
                req.setItemsForUpload(upload.parseRequest(request));
            }

            ViewModel vm = controller.process(req);

            if (vm.hasCookie()) {
                Map<String, String> newCookie = vm.getNewCookie();
                newCookie.keySet().forEach(c -> {
                    Cookie cookie = new Cookie(c, newCookie.get(c));
                    cookie.setMaxAge(3600 * 24 * 30);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                });
            }

            forward(request, response, vm);
        } catch (Throwable t) {
            forward(request, response, new ViewModel("error").addAttribute("error",
                    t.getClass() + " " + t.getMessage() + " " + t.getCause()));
        }
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, ViewModel vm) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(getView(vm));
        setAttributes(req, vm);
        dispatcher.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req, ViewModel vm) {
        vm.getAttributes().keySet().forEach(c -> {
            req.setAttribute(c, vm.getAttribute(c));
        });
    }

    private String getView(ViewModel vm) {
        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        return prefix + vm.getView() + suffix;
    }
}