<%@page import="in.co.rays.model.RoleModel"%>
<%@page import="in.co.rays.ctl.RoleListCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.bean.RoleBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
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
	<div align="center">
		<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.RoleBean"
				scope="request"></jsp:useBean>

			<%
				List list = (List) ServletUtility.getList(request);
			%>
			<h1 align="center" style="color: blue">Role List</h1>

			<div align="center" style="height: 15px; margin-bottom: 12px">
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>

			<tr>
				<td align="center"><label>Name :</label><input
					type="text" name="name"
					value="<%=ServletUtility.getParameter("name", request)%>">
					<input type="submit" name="operation"
					value="<%=RoleListCtl.OP_SEARCH%>"> <input type="submit"
					name="operation" value="<%=RoleListCtl.OP_RESET%>>"></td>
			</tr>
			<table border="1px" width="100%">
				<tr align="center">

					<th><input type="checkbox" id="selectall"></th>
					<th>S.no</th>
					<th>Name</th>
					<th>Description</th>
					<th>Edit</th>

				</tr>

				<%
					Iterator it = list.iterator();

					while (it.hasNext()) {
						
			bean = (RoleBean) it.next();
			RoleModel model = new RoleModel();
						
				%>
				<tr align="center">
					<td><input type="checkbox" class="case"  name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getDescription()%></td>





				</tr>
				<%
					}
				%>


			</table>
			<br>
			<table>

				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="delete"></td>

				</tr>

			</table>

		</form>
	</div>
</body>
</html>