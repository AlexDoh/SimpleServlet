<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User registration</title>
</head>
<body>
<h1>Please fill the registration form (optionally upload image at first) :</h1>
<form method="post" action="<c:url value="/filter/performedaction"/>">
    <input type="hidden" name="action" value="add"/>
    <input type="hidden" name="type" value="User"/>
    <ul style="list-style: none">
        <li>User name:
            <input title="User name" type="text" name="userName"></li>
        <li>User password:
            <input title="User password" type="password" name="userPassword"></li>
        <li>User email:
            <input title="User email" type="email" name="userEmail"></li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 20px">
</form>
<a href="${pageContext.request.contextPath}/filter/login" style="position: relative;bottom: -60px;">Back to Login
    Page</a><br>
</body>
</html>
