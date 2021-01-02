<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Customer"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Customer</title>
</head>
<body>
	<h1>Customer List</h1>

	<%
		List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");
	%>

	<table style="width: 300px">
		<TR>
			<TH>Id</TH>
			<TH>Name</TH>
			<TH>Surname</TH>
			<TH>Phone 1</TH>
			<TH>Phone 2</TH>

		</TR>
		<%
			for (Customer customer : customerList) {
				//List<CustomerPhoneDTO> customerPhoneList = customer.getCustomerPhoneDTOList();
		%>
		<tr>
			<td><%=customer.getId()%></td>
			<td><%=customer.getName()%></td>
		 
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>