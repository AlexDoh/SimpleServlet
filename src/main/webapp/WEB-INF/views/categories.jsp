<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<form method="post" action="<c:url value="/filter/category"/>">
    Find category by id:<br>
    <input type="text" name="id"><br>
    <input type="submit" value="Submit">
</form>
<c:forEach var="e" items="${categories}">
    <a href="${pageContext.request.contextPath}/filter/products?category=<c:out value="${e.name}"/>"
       style="font-size: 20px"><c:out
            value="${e.name}"/></a>
</c:forEach><br>
<a href="${pageContext.request.contextPath}/filter/" style="position: relative;bottom: -40px;">Back</a><br>
</body>
</html>
