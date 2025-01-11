<%@page import="in.co.rays.ctl.PurchaseCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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
		List purchaseList = (List) request.getAttribute("purchaseList");
	%>

	<form action="<%=ORSView.PURCHASE_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.PurchaseBean"
			scope="request" />
		<div align="center">

			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<h1 align="center">Update Stock Purchase</h1>
			<%
				} else {
			%>


			<h1 align="center">
				<font color="navy">Add Stock Purchase</font>
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
					<th align="left">Quantity<span style="color: red">*</span></th>
					<td><input type="text" name="quantity"
						placeholder="Enter Quantity"
						value="<%=(DataUtility.getStringData(bean.getQuantity()).equals("0") ? ""
					: DataUtility.getStringData(bean.getQuantity()))%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("quantity", request)%></font></td>

				</tr>
				<tr>
					<th align="left">Price<span style="color: red">*</span></th>
					<td><input type="text" name="price"
						value="<%=(DataUtility.getStringData(bean.getPrice()).equals("0") ? ""
					: DataUtility.getStringData(bean.getPrice()))%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("price", request)%></font></td>
				<tr>
					<th align="left">Purchase Date<span style="color: red">*</span></th>
					<td><input style="width: 98%" type="text" id="idate"
						name="purchasedate" placeholder="Select purchase date"
						value="<%=DataUtility.getDateString(bean.getPurchaseDate())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("purchasedate", request)%></font></td>

				</tr>
				<tr>
					<th align="left">Order type<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> brandMap = new HashMap<>();
							brandMap.put("hp", "Hp");
							brandMap.put("lenovo", "Lenovo");
							brandMap.put("dell", "Dell");
							brandMap.put("asus", "Asus");
						%> <%=HTMLUtility.getList("order_type", bean.getOrderType(), brandMap)%>
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("order_type", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left"><input type="submit" name="operation"
						value="<%=PurchaseCtl.OP_UPDATE%>"> <input type="submit"
						name="operation" value="<%=PurchaseCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>

					<td colspan="3"><input type="submit" name="operation"
						value="<%=PurchaseCtl.OP_SAVE%>"> <input type="submit"
						name="operation" value="<%=PurchaseCtl.OP_RESET%>"></td>

					<%
						}
					%>
				</tr>


			</table>



		</div>
	</form>

</body>
</html>