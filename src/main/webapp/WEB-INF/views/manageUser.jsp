<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<h2>Enter new values for ${userName} :</h2>
<form method="post" id="update" action="<c:url value="/filter/adminconsole/performedaction"/>"
      style="display:inline-block;margin-right:10px">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="type" value="User"/>
    <input type="hidden" name="userName" value="${userName}"/>
    <ul style="list-style: none">
        <li style="position: relative;bottom: -10px">User password:
            <input title="User password" type="password" name="userPassword">
        </li>
        <li style="position: relative;bottom: -15px">User email:
            <input title="User email" type="email" name="userEmail">
        </li>
        <li style="position: relative;bottom: -20px">User has next roles:
            <c:forEach var="r" items="${roles}">
                <c:out value="${r}"/>
            </c:forEach>
        </li>
        <li style="position: relative;bottom: -25px">
            Set new Roles:
            <select multiple title="Add roles" name="roles" form="update">
                <c:forEach items="${allRoles}" var="r">
                        <option value="${r}">${r}</option>
                </c:forEach>
            </select>
        </li>
    </ul>
    <input type="submit" name="Submit" value="Submit" style="position: relative;bottom: -40px"/>
</form>
</body>
</html>