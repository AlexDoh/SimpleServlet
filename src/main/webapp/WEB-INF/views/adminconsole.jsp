<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin console</title>
</head>
<body>
<h1>Hello, Admin!</h1>
<a href="${pageContext.request.contextPath}/filter/adminconsole/usermanagement"
   style="position: relative;bottom: -5px;"><h3>User management</h3></a><br>
<h3>Category manipulation:</h3>
<form method="POST" action="<c:url value="/filter/performedaction"/>" id="category">
    <input type="hidden" name="type" value="Category"/>
    <ul style="list-style: none">
        <li>Category action:
            <select title="Category action" name="action" form="category" style="position: relative;top: -5px">
                <option value="add">Create</option>
                <option value="update">Update</option>
                <option value="delete">Delete</option>
            </select>
        </li>
        <li>Category name:
            <input title="Category name" type="text" name="categoryName"></li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 20px">
</form>
<h3>Product manipulation:</h3>
<form method="POST" action="<c:url value="/filter/performedadminaction"/>">
    <input type="hidden" name="type" value="Product"/>
    <ul style="list-style: none">
        <li>Choose product operation:
            <input title="Product action" type="text" name="action" style="position: relative;top: -5px">
            (e.g. update, delete, add)
        </li>
        <li>Product name:
            <input title="Product name" type="text" name="productName"></li>
        <li>Product description:
            <input title="Product description" type="text" name="productDescription"></li>
        <li>Category name for product:
            <input title="Product categoryName" type="text" name="productCategoryName"></li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 20px">
</form>
<a href="${pageContext.request.contextPath}/filter/" style="position: relative;bottom: -40px;">Back</a><br>
</body>
</html>
