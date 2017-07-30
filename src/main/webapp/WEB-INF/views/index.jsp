<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>

<h1>Simple JSP collection of users, products and categories</h1>

<a href="<c:url value="/login"/>">Login page</a><br>
<a href="<c:url value="/categories"/>" style="position: relative; bottom: -20px">Categories list</a><br>
<a href="<c:url value="/adminconsole"/>" style="position: relative; bottom: -40px">Admin page</a>
</body>
</html>