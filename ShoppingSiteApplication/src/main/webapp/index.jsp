<%@include file="/WEB-INF/views/template/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<body>
<div style="background-image: url('/resources/images/background.jpg');">
<br><br><br>
	<h3>New User Registration</h3>
	<a href="user/register.htm"><input type="submit" value="Sign Up"></a><br/><br><br>

	<h2>Login</h2>
	<form action="user/login" method="post">
	
		<table>
		
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" class="btn btn-default"/></td>
		</tr>
				
		</table>

	</form>


</body>
</html>

