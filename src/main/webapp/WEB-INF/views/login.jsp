<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="<c:url value="/profile"/>">
    Username:
    <input title="Username" type="text" name="userName" style="position: relative;top: -5px"><br>
    Password:
    <input title="Password" type="password" name="userPassword"><br>
    <button type="submit">LogIn</button>
</form>
<a href="${pageContext.request.contextPath}/" style="position: relative;bottom: -40px;">Back to main page</a>
</body>
</html>