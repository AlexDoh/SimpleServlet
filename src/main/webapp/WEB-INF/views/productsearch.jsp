<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductSearch</title>
</head>
<body>
<h1><c:out value="${product.name}"/></h1><br>
<a href="${pageContext.request.contextPath}/products?category=<c:out value="${product.categoryName}"/>"
   style="position: relative;bottom: -20px;">Back</a><br>
</body>
</html>
