package com.odmytrenko.filter;

import com.odmytrenko.dao.UserDao;
import com.odmytrenko.factory.Factory;
import com.odmytrenko.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserFilter implements Filter {

    private UserDao userDao;
    private Map<String, String> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
        protectedUrls.put("profile", "/filter/profile");
        protectedUrls.put("login", "/filter/login");
        protectedUrls.put("admin", "/filter/adminconsole");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String uri = httpRequest.getRequestURI();

        if (protectedUrls.get("profile").equals(uri) && cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName().toUpperCase();
                String TOKEN = "TOKEN";
                if (TOKEN.equals(name)) {
                    String token = cookie.getValue();
                    try {
                        User user = userDao.findByToken(token);
                        request.setAttribute("user", user);
                        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
                    } catch (NullPointerException e) {
                        throw new RuntimeException("You have corrupted cookies in the browser, please clean them");
                    }
                }
            }
        }
        if (protectedUrls.get("login").equals(uri) && cookies != null) {
            if (Arrays.stream(cookies).anyMatch(p -> p.getName().equals("token"))) {
                User user = userDao.findByToken(Arrays.stream(cookies).filter(p -> p.getName().equals("token")).
                        findFirst().get().getValue());
                if (user != null) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
                }
            }
        }
        if (protectedUrls.get("admin").equals(uri) && cookies != null) {
            if (Arrays.stream(cookies).anyMatch(p -> p.getName().equals("token"))) {
                User user = userDao.findByToken(Arrays.stream(cookies).filter(p -> p.getName().equals("token")).
                        findFirst().get().getValue());
                if (user.isAdmin()) {
                    request.getRequestDispatcher("/WEB-INF/views/adminconsole.jsp").forward(request, response);
                } else {
                    throw new RuntimeException("User doesn't have privileges to enter this page");
                }
            } else {
                throw new RuntimeException("You must be logged on to enter this page");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
