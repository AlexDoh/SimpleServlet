<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CategorySearch</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/products?category=<c:out value="${category.name}"/>"
   style="font-size: 20px"><c:out
        value="${category.name}"/></a><br>
<a href="${pageContext.request.contextPath}/categories" style="position: relative;bottom: -20px;">Back</a><br>
</body>
</html>
