<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>

<h1>Simple JSP collection of users, products and categories</h1>

<a href="<c:url value="/filter/login"/>">Login page</a><br>
<a href="<c:url value="/filter/categories"/>" style="position: relative; bottom: -20px">Categories list</a><br>
<a href="<c:url value="/filter/adminconsole"/>" style="position: relative; bottom: -40px">Admin page</a>
<%
    if (request.getParameter("logout") != null) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token")) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }
%>
</body>
</html>