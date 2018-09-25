<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.text" var="text"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Holograms list</title>
</head>

<body>

	<h1>Hologram in stock</h1>

	<table>
		<tr id="tableTop" align="left">
			<th><fmt:message key="waferID" bundle="${text}" /></th>
			<th><fmt:message key="waferThickness" bundle="${text}" /></th>
			<th><fmt:message key="dimension" bundle="${text}" /></th>
			<th><fmt:message key="recDate" bundle="${text}" /></th>
			<th><fmt:message key="project" bundle="${text}" /></th>
			<th><fmt:message key="cwlTarget" bundle="${text}" /></th>
		</tr>
		<tr></tr>
		<c:forEach var="rec" items="${records}">
			<tr id="table">
				<td width="80"><a href="servlet?command=loadHologram&clickedID=${rec.waferID}"><c:out value="${rec.waferID}" /></a></td>
				<td width="80"><c:out value="${rec.waferThickness}" /></td>
				<td width="80"><c:out value="${rec.waferDimention}" /></td>
				<td width="180"><c:out value="${rec.recDate}" /></td>
				<td width="120"><c:out value="${rec.project.name}" /></td>
				<td width="120"><c:out value="${rec.targetCWL}" /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>