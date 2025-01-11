<%@page import="in.co.rays.bean.DoctorBean"%>
<%@page import="in.co.rays.ctl.DoctorListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
	<form action="<%=ORSView.DOCTOR_LIST_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.DoctorBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1>Doctor list</h1>

			<div align="center" style="height: 15px; margin-bottom: 12px">
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Expertise :</b></label> <input
						type="text" name="expertise"
						value="<%=ServletUtility.getParameter("expertise", request)%>">
						<input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_SEARCH%>"> <input type="submit"
						name="operation" value="<%=DoctorListCtl.OP_RESET%>"></td>
				</tr>
			</table>


			<table border="1px" width="100%">
				<tr bgcolor="yellow">
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No</th>
					<th>Name</th>
					<th>DOB</th>
					<th>Mobile No</th>
					<th>Expertise</th>
					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = (DoctorBean) it.next();
				%>

				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getDob()%></td>
					<td><%=bean.getMobile()%></td>
					<td><%=bean.getExpertise()%></td>
					<td><a href="<%=ORSView.DOCTOR_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
					<%
						}
					%>
				
			</table>
			<br>
			<table>
				<tr>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_NEW%>"></td>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_DELETE%>"></td>
					<td style="text-align: right;"><input type="submit"
						name="operation" value="<%=DoctorListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
				}
				if (list.size() == 0) {
			%>
			<br>
			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
				}
			%>

		</div>
	</form>
</body>
</html>