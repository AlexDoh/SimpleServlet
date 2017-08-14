<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful</title>
</head>
<body>
<h1>Action successfully performed!<br>
    <%
        if (request.getParameter("type") != null) {
            request.getParameter("type");
        } else {
    %>
    ${requestScope.type}
    <%
        }
    %>
    ${requestScope.object.name} processed</h1>
<a href="${pageContext.request.contextPath}/filter/"
   style="position: relative;bottom: -20px;">Back to main page</a><br>
</body>
</html>
