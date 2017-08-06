<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<form method="post" action="<c:url value="/product"/>">
    Find product by id:
    <input title="Find product by id:" type="text" name="id" style="position: relative;top: -5px"><br>
    Category of product:
    <input title="Category of product:" type="text" name="category" value="<%= request.getParameter("category") %>"><br>
    <input type="submit" value="Submit" style="position: relative;bottom: -10px">
</form>
<c:forEach var="e" items="${products}">
    <h1><c:out value="${e.name} (${e.description})"/></h1>
</c:forEach>
<a href="${pageContext.request.contextPath}/categories" style="position: relative;bottom: -20px">Back</a><br>
</body>
</html>
