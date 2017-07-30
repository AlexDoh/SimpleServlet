package com.odmytrenko.servlet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainServletManagement {


    private Map<String, String> mapOfFiles = new HashMap<>();

    public MainServletManagement(String[] servletContent) {
        mapOfFiles = Arrays.stream(servletContent).collect(Collectors.toMap(
                s -> s, s -> '/' + s.replace(".jsp", "")));
        mapOfFiles.put("index.jsp", "/");
    }

    public String getCategoriesJSP() {
        return mapOfFiles.get("categories.jsp");
    }

    public String getIndexJSP() {
        return mapOfFiles.get("index.jsp");
    }

    public String getLoginJSP() {
        return mapOfFiles.get("login.jsp");
    }

    public String getWelcomeJSP() {
        return mapOfFiles.get("welcome.jsp");
    }

    public String getProductsJSP() {
        return mapOfFiles.get("products.jsp");
    }

    public String getAdminJSP() {
        return mapOfFiles.get("adminconsole.jsp");
    }

    public String getAdminActionJSP() {
        return mapOfFiles.get("performedadminaction.jsp");
    }

}
