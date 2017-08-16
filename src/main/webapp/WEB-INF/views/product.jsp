<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductSearch</title>
</head>
<body>
<h1><c:out value="${product.name}"/></h1><br>
<h3 style="position: relative;bottom: -20px;">This category can be found by link :</h3><br>
<a href="${pageContext.request.contextPath}/filter/product?id=${product.id}"><c:out
        value="${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}
        /filter/product?id=${product.id}"/></a><br>
<a href="${pageContext.request.contextPath}/filter/products?categoryId=${categoryId}"
   style="position: relative;bottom: -40px;">To category</a><br>
</body>
</html>
