<%@page import="in.co.rays.ctl.SubjectCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
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

	<%
		List courseList = (List) request.getAttribute("courseList");
	%>
	<form action="<%=ORSView.SUBJECT_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.SubjectBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1>
				<font color="navy">Add Subject</font>
			</h1>
			
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
						placeholder="Enter Subject Name"
						value="<%=DataUtility.getStringData(bean.getName())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Course Name <span style="color: red">*</span></th>

					<td><%=HTMLUtility.getList("courseId", bean.getCourseName(), courseList)%></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("courseName", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Description <span style="color: red">*</span></th>
					<td><input type="text" name="description"
						placeholder="Enter description"
						value="<%=DataUtility.getStringData(bean.getDescription())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td colspan="3"><input type="submit" name="operation"
						value="<%=SubjectCtl.OP_SAVE%>"> <input type="submit"
						name="operation" value="<%=SubjectCtl.OP_RESET%>"></td>

				</tr>

			</table>
		</div>

	</form>

</body>
</html>