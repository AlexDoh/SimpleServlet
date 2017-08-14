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
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Stream;

public class ProfileFilter implements Filter {

    private UserDao userDao;
    private Set<String> protectedUrl = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
        protectedUrl.add("/filter/profile");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String uri = httpRequest.getRequestURI();

        if (protectedUrl.contains(uri) && cookies != null) {
            Stream.of(cookies).forEach(c -> {
                String name = c.getName().toUpperCase();
                String TOKEN = "TOKEN";
                if (TOKEN.equals(name)) {
                    String token = c.getValue();
                    try {
                        User user = userDao.findByToken(token);
                        request.setAttribute("user", user);
                        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
                    } catch (NullPointerException e) {
                        throw new RuntimeException("You have corrupted cookies in the browser, please clean them");
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
