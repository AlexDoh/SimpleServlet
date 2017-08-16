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
<h3>Category manipulation:</h3><br>
<h3 style="display:inline-block;margin-right:15px">Present Categories:</h3>
<c:forEach var="c" items="${categories}">
    <h4 style="display:inline-block;margin-right:5px"><i>${c.name}</i></h4>
</c:forEach>
<form method="post" action="<c:url value="/filter/adminconsole/performedaction"/>" id="category">
    <input type="hidden" name="type" value="Category"/>
    <ul style="list-style: none">
        <li>Category action:
            <select title="Category action" name="action" form="category" style="position: relative;top: -5px">
                <option value="add">Create</option>
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
<h3 style="display:inline-block;margin-right:15px">Present products:</h3>
<c:forEach var="p" items="${products}">
    <h4 style="display:inline-block;margin-right:5px"><i>${p.name}</i></h4><h5
        style="display:inline-block;margin-right:5px">(${p.category.name})</h5>
</c:forEach>
<form method="post" action="<c:url value="/filter/adminconsole/performedaction"/>" id="product">
    <input type="hidden" name="type" value="Product"/>
    <ul style="list-style: none">
        <li>Choose product operation:
            <select title="Product action" name="action" form="product" style="position: relative;top: -5px">
                <option value="add">Create</option>
                <option value="update">Update</option>
                <option value="delete">Delete</option>
            </select>
        </li>
        <li style="position: relative;bottom: -5px">Product name:
            <input title="Product name" type="text" name="productName"></li>
        <li style="position: relative;bottom: -10px">Product description:
            <input title="Product description" type="text" name="productDescription"></li>
        <li style="position: relative;bottom: -15px">Category name for product:
            <select title="Product categoryName" name="productCategoryId" form="product">
                <c:forEach var="c" items="${categories}">
                    <option value="${c.id}">
                        <c:out value="${c.name}"/>
                    </option>
                </c:forEach>
            </select>
        </li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 5px">
</form>
<a href="${pageContext.request.contextPath}/filter/" style="position: relative;bottom: -35px;">Back</a><br>
</body>
</html>
