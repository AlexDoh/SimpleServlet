<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin console</title>
</head>
<body>
<h1>Hello, Admin!</h1>
<h3>User manipulation:</h3>
<form method="post">
    <ul style="list-style: none">
        <li>Choose user operation:
            <input title="User action" type="text" name="useraction" style="position: relative;top: -5px">
            (e.g. update, delete, add)
        </li>
        <li>User name:
            <input title="User name" type="text" name="userName"></li>
        <li>User password:
            <input title="User password" type="password" name="userPassword"></li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 20px">
</form>
<h3>Category manipulation:</h3>
<form method="post">
    <ul style="list-style: none">
        <li>Choose category operation:
            <input title="Category action" type="text" name="categoryaction" style="position: relative;top: -5px">
            (e.g. update, delete, add)
        </li>
        <li>Category name:
            <input title="Category name" type="text" name="categoryName"></li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 20px">
</form>
<h3>Product manipulation:</h3>
<form method="post">
    <ul style="list-style: none">
        <li>Choose product operation:
            <input title="Product action" type="text" name="productaction" style="position: relative;top: -5px">
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
<a href="${pageContext.request.contextPath}/" style="position: relative;bottom: -40px;">Back</a><br>
</body>
</html>