<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h3>Hello <c:out value="${user.name}"/>!!!</h3><br>
<c:set var = "image" value = "${pageContext.request.contextPath}/images/${user.name}.jpg"/>
<img src="<c:out value = "${image}"/>" alt="(User has no profile image)" height="200" width="200">
<form method="get" action="<c:url value="/filter/"/>">
    <button name="logout" style="position: relative;bottom: -40px;">LogOut</button>
</form><br>
<h3>Select profile image to upload :</h3>
<form method="post" action="<c:url value="/filter/profile/imageupload"/>" enctype="multipart/form-data">
    <input type="hidden" name="userName" value="<c:out value="${user.name}"/>"/>
    Select image to upload:
    <input type="file" name="image"/><br>
    <input type="submit" value="Upload"/>
</form>
<a href="${pageContext.request.contextPath}/filter/" style="position: relative;bottom: -60px;">Back to main page</a>
</body>
</html>
