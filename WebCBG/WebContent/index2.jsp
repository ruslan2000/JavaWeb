<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="properties.text" var="text" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OptiGrate CBG production</title>
</head>
<body>

	<h1>Welcome to Holograms viewer</h1>

	<br>

	<form name="main" method="post" action="servlet">
		<input type="hidden" name="command"	value="search">
		<select name="gratingType">
			<option value="0" selected>Please select</option>
			<option value="CBG">CBG</option>
			<option value="RBG">RBG</option>
			<option value="TBG">TBG</option>
			<option value="TCBG">CTBG</option>
			<option value="BNF">BNF</option>
			<option value="BPF">BPF</option>
		</select> 
		<br> 
		<br> 
		
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
				<td><input type="submit" value="<fmt:message key="search" bundle="${text}"/>"></td>
			</tr>
		</table>

	</form>

</body>
</html>