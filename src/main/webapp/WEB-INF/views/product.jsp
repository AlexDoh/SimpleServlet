<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductSearch</title>
</head>
<body>
<h1><c:out value="${product.name}"/></h1><br>
<h3 style="position: relative;bottom: -20px;">This category can be found by link :</h3><br>
<a href="${pageContext.request.contextPath}/filter/product?category=${product.categoryName}&id=${product.id}"><c:out
        value="${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}
        /filter/product?category=${product.categoryName}&id=${product.id}"/></a><br>
<a href="${pageContext.request.contextPath}/filter/products?category=<c:out value="${product.categoryName}"/>"
   style="position: relative;bottom: -40px;">Back</a><br>
</body>
</html>
