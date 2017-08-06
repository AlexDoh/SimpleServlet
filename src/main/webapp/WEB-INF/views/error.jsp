<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Error</h1>
<h3 style="position: relative;bottom: -20px;">Message : <c:out value="${error}"/></h3>
<a href="${pageContext.request.contextPath}/" style="position: relative;bottom: -40px;">Back to main page</a>
</body>
</html>
