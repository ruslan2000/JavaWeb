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
<title>CBGs list</title>
</head>

<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>

	<h1>CBG in stock</h1>
	

	<table>
		<tr id="tableTop" align="left">
			<th><a href="servlet?command=sortByWaferID"><fmt:message key="waferID" bundle="${text}" /></a></th>
			<th><fmt:message key="waferThickness" bundle="${text}" /></th>
			<th><fmt:message key="dimension" bundle="${text}" /></th>
			<th><a href="servlet?command=sortByRecDate"><fmt:message key="recDate" bundle="${text}" /></a></th>
			<th><a href="servlet?command=sortByProjectID"><fmt:message key="project" bundle="${text}" /></a></th>
			<th><a href="servlet?command=sortByWavelength"><fmt:message key="cwlTarget" bundle="${text}" /></a></th>
			<th><fmt:message key="cwlTest" bundle="${text}" /></th>
			<th><fmt:message key="m2x" bundle="${text}" /></th>
			<th><fmt:message key="m2y" bundle="${text}" /></th>
		</tr>
		<tr></tr>
		<c:forEach var="rec" items="${records}">
			<tr id="table">
				<td width="80"><a href="servlet?command=loadCBG&clickedID=${rec.waferID}" > <c:out value="${rec.waferID}" /> </a></td>
				<td width="80"><c:out value="${rec.waferThickness}" /></td>
				<td width="80"><c:out value="${rec.waferDimention}" /></td>
				<td width="180"><c:out value="${rec.recDate}" /></td>
				<td width="120"><c:out value="${rec.project.name}" /></td>
				<td width="120"><c:out value="${rec.project.CWL}" /></td>
				<td width="120"><c:out value="${rec.testCWL}" /></td>
				<td width="120"><c:out value="${rec.m2x}" /></td>
				<td width="120"><c:out value="${rec.m2y}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>