<%@page import="in.co.rays.ctl.LoginCtl"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
<center>
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">

		<table>
<br><br>
			<tr>
				<th align="center">Login -</th>
				<td><input type="email" name="login"
					placeholder="Enter your LoginId"></td>
			</tr>

			<tr>
				<th align="center">Password -</th>
				<td><input type="password" name="password"
					placeholder="Enter your password"></td>
			</tr>


			<tr>
				<th></th>
				<td><input type="submit" name="operation" value="SignIn">
				</td>

			</tr>
		</table>

	</form>
</center>
</body>
</html>