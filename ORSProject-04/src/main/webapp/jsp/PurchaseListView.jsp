<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.bean.PurchaseBean"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
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
		<form action="<%=ORSView.PURCHASE_LIST_CTL%>" method="post">
			<h1>Purchase List</h1>

			<jsp:useBean id="bean" class="in.co.rays.bean.PurchaseBean"
				scope="request"></jsp:useBean>

			<%
				List list = ServletUtility.getList(request);
			%>

			<table border="1px" width="100%">
				<tr>
					<th>S.No.</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Purchase Date</th>
					<th>Order Type</th>
				</tr>

				<%
					Iterator it = list.iterator();
					while (it.hasNext()) {
						bean = (PurchaseBean) it.next();
				%>

				<tr align="center">
					<td><%=bean.getId()%></td>
					<td><%=bean.getQuantity()%></td>
					<td><%=bean.getPrice()%></td>
					<td><%=bean.getPurchaseDate()%></td>
					<td><%=bean.getOrderType()%></td>
				</tr>

				<%
					}
				%>

			</table>
		</form>
	</div>

</body>
</html>