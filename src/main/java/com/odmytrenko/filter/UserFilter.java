package com.odmytrenko.filter;

import com.odmytrenko.dao.UserDao;
import com.odmytrenko.factory.Factory;
import com.odmytrenko.model.User;
import com.odmytrenko.service.UserService;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserFilter implements Filter {

    private UserDao userDao;
    private List<String> protectedUrl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
        protectedUrl.add("/filter/profile");
//        protectedUrl.add("/admin"); TO \DO HOMEWORKdddddddddd
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String uri = httpRequest.getRequestURI();

        if (protectedUrl.contains(uri)) {
            if (cookies != null) {
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
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
