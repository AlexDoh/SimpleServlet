<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<h1>List of users</h1>
<c:forEach var="u" items="${users}">
    <p>
    <h4 style="display:inline-block;margin-right:5px">Account name: </h4>
    <h3 style="display:inline-block;margin-right:5px"><i><c:out value="${u.name}"/></i></h3>
    <form method="post" id="edit" action="<c:url value="/filter/adminconsole/manageuser"/>"
          style="display:inline-block;margin-right:10px">
        <input type="hidden" name="password" value="${u.password}"/>
        <button name="userName" title="Edit user" form="edit" value="<c:out value="${u.name}"/>">Edit</button>
    </form>
    <form method="post" id="delete" action="<c:url value="/filter/adminconsole/deleteuser"/>"
          style="display:inline-block;margin-right:10px">
        <button name="userName" title="Delete user" form="delete" value="<c:out value="${u.name}"/>">Delete</button>
    </form>
    </p>
</c:forEach>
</body>
</html>
