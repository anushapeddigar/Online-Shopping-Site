<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Product Form</title>


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

	<a href="${contextPath}/user/">ADMIN Home</a><br/>
	<br>
<br>
	<h2>Adding a New Product</h2>


	<form:form action="${contextPath}/product/add" method="post"
		modelAttribute="product" >
<!-- enctype="multipart/form-data" -->
		<table>
			<tr>
				<td>User Name:</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.userID}" /></td>
			</tr>

			<tr>
				<td>Select a Category:</td>
				<td>
						
			<select name="productCategory">
				<option value="Electronics">Electronics</option>
   <option value="Books">Books</option>
   <option value="Kitchen">Kitchen</option>
   <option value="HealthCare">HealthCare</option>
   <option value="BabyCare">BabyCare</option>
			</select>
						
						</td>
			</tr>
			
			

			<tr>
				<td>Product Name:</td>
				<td><form:input type="text" id ="productName" path="productName" size="30" required="required"/>
				<font color="red"><form:errors path="productName" /></font></td>
			</tr>

			
			
			
			<tr>
				<td>Product Description:</td>
				<td><form:input type="text" path="description" size="30" required="required"/>
				<font color="red"><form:errors path="description" /></font></td>
			</tr>
			
			<tr>
				<td>Product Price:</td>
				<td><form:input type="number" path="price" size="30" required="required"/>
				<font color="red"><form:errors path="price" /></font></td>
			</tr>
<tr>
</tr><br>
			<tr>
				<td colspan="2"><input type="submit" value="Add Product" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>