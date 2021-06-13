<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Products</title>

<!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    
    
    <!-- FONT AWESOME -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- normalize css -->
    <link href="<c:url value="/resources/css/normalize.css"/>" rel="stylesheet">
    
     <!-- Angular.JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
    
     <!-- main css -->
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/carousel.css"/>" rel="stylesheet">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/"><input type="submit" value="Logout"></a><br/><br>
	<br>

 <form:form action="${contextPath}/cart/insert" method="post" commandName="cart">
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Product Name</b></td>
			<td><b>Product Description</b></td>
			<td><b>Seller Name</b></td>
			<td><b>Category</b></td>
			<td><b>Price</b></td>
			<td><b>Options</b></td>
		</tr>
		<c:forEach var="prod" items="${products}">
		
		
			<tr>
				<td>${prod.productName}</td>
				<td>${prod.description}</td>
				<td>${prod.user.username}</td>
				<td>${prod.productCategory}</td>
                <td>${prod.price}</td>
                <td>
                
				<button name="id" value="${prod.id}" type="submit">Add to Cart</button>
                
                
                
                </td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- CHECKOUT BUTTON -->
	
	
	 </form:form> 
</body>
</html>