<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h3>Hello <c:out value="${user.name}"/>!!!</h3>
<form method="GET" action="<c:url value="/filter/"/>">
    <button name="logout" style="position: relative;bottom: -40px;">LogOut</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/filter/" style="position: relative;bottom: -60px;">Back to main page</a>
</body>
</html>
