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
import java.util.HashSet;
import java.util.Set;

public class LoginFilter implements Filter {
    private UserDao userDao;
    private Set<String> protectedUrl = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
        protectedUrl.add("/filter/login");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String uri = httpRequest.getRequestURI();

        if (protectedUrl.contains(uri) && cookies != null) {
            if (Arrays.stream(cookies).anyMatch(p -> p.getName().equals("token"))) {
                User user = userDao.findByToken(Arrays.stream(cookies).filter(p -> p.getName().equals("token")).
                        findFirst().get().getValue());
                if (user != null) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
