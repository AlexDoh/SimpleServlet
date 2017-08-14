<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="POST" action="<c:url value="/filter/profile"/>">
    Username:
    <input title="Username" type="text" name="userName" style="position: relative;top: -5px"><br>
    Password:
    <input title="Password" type="password" name="userPassword"><br>
    <button type="submit">LogIn</button>
</form><br>
<h2>If you are not registered user, please click the registration button :</h2>
<form action="<c:url value="/filter/registration"/>">
<button type="submit" style="height:50px;width:200px">Registration</button>
</form><br>
<a href="${pageContext.request.contextPath}/filter/" style="position: relative;bottom: -40px;">Back to main page</a>
</body>
</html>