<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="properties.text" var="text" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>OptiGrate CBG Stock</title>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>

	<h1>Welcome to CBGs in Stock Viewer</h1>


	<form action="servlet" method="post">
		<input type="hidden" name="command" value="searchInStock" />
		<table style="table-layout:fixed;">
			<tr>
				<th><fmt:message key="waferID" bundle="${text}" /></th>
				<td><input name="searchID"><br></td>
			</tr>			
			<tr>
				<th><fmt:message key="projectName" bundle="${text}" /></th>
				<td><input name="searchProject"><br></td>
			</tr>
			<tr>
				<th><fmt:message key="wavelength" bundle="${text}" /></th>
				<td><input name="searchWL"><br /></td>
				<th><fmt:message key="+/-" bundle="${text}" /></th>
				<td width="40"><input name="searcDeltahWL"><br /></td>
			</tr>
			<tr>
				<th><fmt:message key="fwhm" bundle="${text}" /></th>
				<td><input name="searchFWHM"><br /></td>
				<th><fmt:message key="+/-" bundle="${text}" /></th>
				<td width="40"><input name="searchDeltaFWHM"><br /></td>
			</tr>
			<tr>
				<th><fmt:message key="waferThickness" bundle="${text}" /></th>
				<td><input name="searchThickness"><br /></td>
				<th><fmt:message key="+/-" bundle="${text}" /></th>
				<td width="40"><input name="searchDeltaThickness"><br /></td>
			</tr>
			<tr>
				<th><fmt:message key="dispersion" bundle="${text}" /></th>
				<td><input name="searchDispersion"><br /></td>
				<th><fmt:message key="+/-" bundle="${text}" /></th>
				<td width="40"><input name="searchDeltaDispersion"><br /></td>
			</tr>

			<tr>

				<td></td>
				<td><p>
						<input type="submit"
							value="<fmt:message key="searchInStock" bundle="${text}"/>">
					</p></td>
				<td></td>
				<td><input type="checkbox" name="checkBoxReady" value="">Ready To Go</td>
			</tr>
		</table>
	</form>


</body>
</html>