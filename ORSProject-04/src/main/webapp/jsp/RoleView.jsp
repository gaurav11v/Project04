<%@page import="in.co.rays.ctl.RoleCtl"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
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
	<form action="<%=ORSView.ROLE_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.RoleBean"
			scope="request" />


<div align="center">

			<h1 style="color: blue">Role List</h1>

			<br> <br>

			<!-- Success and Error Messages -->
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>

			<!-- Hidden Fields -->
			<input type="hidden" name="id" value="<%=bean.getId()%>" /> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>" />
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>" /> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>" />
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>" />

			<table>
				<tr>
					<th align="left">Name <span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter Role Name"
						value="<%=DataUtility.getStringData(bean.getName())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>

				</tr>

				<tr>
					<th align="left">Description<span style="color: red">*</span></th>
					<td><input type="text" name="description"
						placeholder="Enter Description of Role"
						value="<%=DataUtility.getStringData(bean.getDescription())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("description", request)%></font></td>

				</tr>

				<tr>
					<td></td>
					<td colspan="3"><input type="submit" name="operation"
						value="<%=RoleCtl.OP_SAVE%>" /> <input type="submit"
						name="operation" value="<%=RoleCtl.OP_RESET%>" /></td>
				</tr>

			</table>
		</div>


	</form>
</body>
</html>