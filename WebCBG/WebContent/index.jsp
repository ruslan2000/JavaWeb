<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="properties.text" var="text" />

<jsp:useBean id="pagePath" class="by.ruslan.hologram.utill.PagesPath" scope="session"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>OptiGrate CBG production</title>
</head>
<body>

	<h1>Welcome to CBGs viewer</h1>


	<form action="servlet" method="post">
		<input type="hidden" name="command" value="search">
		<table>
			<tr>
				<th><fmt:message key="waferID" bundle="${text}" /></th>
				<th><fmt:message key="projectName" bundle="${text}" /></th>
				<th><fmt:message key="wavelength" bundle="${text}" /></th>
				<th></th>
			</tr>
			<tr>
				<td><input name="searchID"><br /></td>
				<td><input name="searchProject"><br /></td>
				<td><input name="searchWL"><br /></td>
				<td><input type="submit"
					value="<fmt:message key="search" bundle="${text}"/>"></td>
			</tr>
		</table>
	</form>

	<p>
		<a href="servlet?command=goToStock">Go to CBG's Stock</a>
	</p>
	
	<p>
		<a href="servlet?command=goToLensesStock">Go to Lens's Stock</a>
	</p>
	<p>
		<a href=servlet?command=refresh>Refresh</a>
	</p>

</body>
</html>