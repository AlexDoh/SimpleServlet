<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<c:forEach var="e" items="${users}">
    <a href="${pageContext.request.contextPath}/filter/products?category=<c:out value="${e.name}"/>"
       style="font-size: 20px"><c:out
            value="${e.name}"/></a>
</c:forEach><br>
</body>
</html>
