<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<form method="post">
    Find category by id:<br>
    <input type="text" name="findbyid"><br>
    <input type="submit" value="Submit">
</form>
<c:forEach var="e" items="${categories}">
    <a href="${pageContext.request.contextPath}/products?category=<c:out value="${e.name}"/>"
       style="font-size: 20px"><c:out
            value="${e.name}"/></a>
</c:forEach><br>
<a href="${pageContext.request.contextPath}/" style="position: relative;bottom: -40px;">Back</a><br>
</body>
</html>
