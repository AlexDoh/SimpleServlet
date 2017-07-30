package com.odmytrenko.servlet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainServletManagement {

    private Map<String, String> mapOfFiles = new HashMap<>();
    private Map<String, String> mapOfParameters = new HashMap<>();

    public MainServletManagement(String[] servletContent) {
        mapOfFiles = Arrays.stream(servletContent).collect(Collectors.toMap(
                s -> s, s -> '/' + s.replace(".jsp", "")));
        mapOfFiles.put("index.jsp", "/");

        mapOfParameters.put("findbyid", "findbyid");
        mapOfParameters.put("category", "category");
        mapOfParameters.put("useraction", "useraction");
        mapOfParameters.put("categoryaction", "categoryaction");
        mapOfParameters.put("productaction", "productaction");
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

    public String getParametrFindById() {
        return mapOfParameters.get("findbyid");
    }

    public String getParametrCategory() {
        return mapOfParameters.get("category");
    }

    public String getParametrUserAction() {
        return mapOfParameters.get("useraction");
    }

    public String getParametrCategoryAction() {
        return mapOfParameters.get("categoryaction");
    }

    public String getParametrProductAction() {
        return mapOfParameters.get("productaction");
    }
}
