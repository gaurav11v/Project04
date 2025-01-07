<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
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
		List studentList = (List) request.getAttribute("studentList");
	%>

	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
		<div align="center">
			<h1 style="color: blue">Add Marksheet</h1>

			<jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
				scope="request" />

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
					<th align="left">Roll No<span style="color: red">*</span></th>
					<td><input type="text" name="roll_no"
						placeholder="Enter Roll No"
						value="<%=DataUtility.getStringData(bean.getRollNo())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("roll_no", request)%></font></td>

				</tr>

				<tr>
					<th align="left">Student Name <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("studentId", DataUtility.getStringData(bean.getStudentId()), studentList)%></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("studentId", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Physics <span style="color: red">*</span></th>
					<td><input type="text" name="physics"
						placeholder="Enter physics No"
						value="<%=(DataUtility.getStringData(bean.getPhysics()).equals("0") ? ""
					: DataUtility.getStringData(bean.getPhysics()))%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("physics", request)%></font></td>

				</tr>

				<tr>
					<th align="left">Chemistry <span style="color: red">*</span></th>
					<td><input type="text" name="physics"
						placeholder="Enter chemistry No"
						value="<%=DataUtility.getStringData(bean.getChemistry())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>

				</tr>

				<tr>
					<th align="left">Maths <span style="color: red">*</span></th>
					<td><input type="text" name="maths"
						placeholder="Enter maths No"
						value="<%=DataUtility.getStringData(bean.getMaths())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("maths", request)%></font></td>

				</tr>

			</table>

		</div>
	</form>

</body>
</html>