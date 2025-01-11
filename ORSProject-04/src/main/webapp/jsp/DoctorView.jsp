<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.DoctorCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
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
	<%
		List expertiseList = (List) request.getAttribute("expertiseList");
	%>
	
	<form action="<%=ORSView.DOCTOR_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.DoctorBean"
			scope="request" />
		<div align="center">

			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<h1 align="center">Update Doctor</h1>
			<%
				} else {
			%>


			<h1 align="center">
				<font color="navy">Add Doctor</font>
			</h1>

			<%
				}
			%>
			<!-- Success and Error Messages -->
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>

			<!-- Hidden Fields -->
			
			<input type="hidden" name="id" value="<%=bean.getId()%>" /> 
			<table>

				<tr>
					<th align="left">Name :<span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter Doctor's name"
						value="<%=DataUtility.getStringData(bean.getName())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>

				</tr>
				
				<tr>
					<th align="left">DOB :<span style="color: red">*</span></th>
					<td><input style="width: 98%" type="text" id="udate"
						name="dob" placeholder="Enter Date of birth"
						value="<%=DataUtility.getDateString(bean.getDob())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font></td>

				</tr>
				
				<tr>
					<th align="left">Mobile No :<span style="color: red">*</span></th>
					<td><input type="text" name="mobile"
						placeholder="Enter Mobile no"
						value="<%=DataUtility.getStringData(bean.getMobile())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font></td>

				</tr>
				
				<tr>
					<th align="left">Expertise<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> ExpertiseMap = new HashMap<>();
							ExpertiseMap.put("Cardiologist", "Cardiologist");
							ExpertiseMap.put("Gynaelogist", "Gynaelogist");
							ExpertiseMap.put("Oncologist", "Oncologist");
							ExpertiseMap.put("Physician", "Physician");
							ExpertiseMap.put("Pediatrician", "Pediatrician");
							ExpertiseMap.put("Neurologist", "Neurologist");
						%> <%=HTMLUtility.getList("expertise", bean.getExpertise(), ExpertiseMap)%>
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("expertise", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left"><input type="submit" name="operation"
						value="<%=DoctorCtl.OP_UPDATE%>"> <input type="submit"
						name="operation" value="<%=DoctorCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>

					<td colspan="3"><input type="submit" name="operation"
						value="<%=DoctorCtl.OP_SAVE%>"> <input type="submit"
						name="operation" value="<%=DoctorCtl.OP_RESET%>"></td>

					<%
						}
					%>
				</tr>


			</table>


		</div>
	</form>

</body>
</html>