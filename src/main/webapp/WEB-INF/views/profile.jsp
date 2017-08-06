<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h3>Hello <c:out value="${user.name}"/>!!!</h3>
<a href="${pageContext.request.contextPath}/" style="position: relative;bottom: -40px;">Back to main page</a>
</body>
</html>
