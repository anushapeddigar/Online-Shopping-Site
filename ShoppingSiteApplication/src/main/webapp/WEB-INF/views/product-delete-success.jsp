<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PRODUCT DELETED</title>

</head>
<body>
<a href="${contextPath}"><input type="submit" value="Back"></a><br/>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


	<h2>Product Deleted Successfully: ${product.productName}</h2>
</body>
</html>