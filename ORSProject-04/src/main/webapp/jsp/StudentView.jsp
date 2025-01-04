<%@page import="in.co.rays.ctl.StudentCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="com.google.protobuf.Method"%>
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
		List collegeList = (List) request.getAttribute("collegeList");
	%>
	<form action="<%=ORSView.STUDENT_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.StudentBean"
			scope="request" />
		<div align="center">

			<h1 style="color: blue">Add Student</h1>

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
					<th align="left">First Name <span style="color: red">*</span></th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Last Name <span style="color: red">*</span></th>
					<td><input type="text" name="lastName"
						placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>

				<tr>
					<th align="left">DOB<span style="color: red">*</span></th>
					<td><input style="width: 98%" type="text" id="udate"
						name="dob" placeholder="Select Date of Birth"
						value="<%=DataUtility.getDateString(bean.getDob())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font></td>

				</tr>

				<tr>
					<th align="left">Gender<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> genderMap = new HashMap<>();
							genderMap.put("male", "Male");
							genderMap.put("female", "Female");
						%> <%=HTMLUtility.getList("gender", bean.getGender(), genderMap)%>
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Mobile No<span style="color: red">*</span></th>
					<td><input type="text" name="mobileNo"
						placeholder="Enter Mobile No."
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>

				</tr>

				<tr>
					<th align="left">Email <span style="color: red">*</span></th>
					<td><input type="email" name="email" placeholder="Enter Email"
						value="<%=DataUtility.getStringData(bean.getEmail())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("email", request)%></font></td>

				</tr>

				<tr>
					<th align="left">College Name<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("collegeId", bean.getCollegeName(), collegeList)%></td>
			<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("College Name", request)%></font></td>
			
			<br>
			<tr>
				<th></th>
				<td colspan="3"><input type="submit" name="operation"
					value="<%=StudentCtl.OP_SAVE%>"> <input type="submit"
					name="operation" value="<%=StudentCtl.OP_RESET%>"></td>

			</tr>

		</div>
		</table>
	</form>
</body>
</html>