<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful</title>
</head>
<body>
<h1>Action successfully performed!<br> <%= request.getParameterNames().nextElement().substring(
        0, request.getParameterNames().nextElement().indexOf("action")) %> ${requestScope.object.name} processed</h1>
<a href="${pageContext.request.contextPath}/adminconsole"
   style="position: relative;bottom: -20px;">Back</a><br>
</body>
</html>
