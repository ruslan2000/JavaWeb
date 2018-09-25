<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="properties.text" var="text" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Login page</title>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerLogin.jspf" %>
	<div align="center">
	<h1>
		<fmt:message key="welcome" bundle="${text}" />
	</h1>
	<form action="servlet" method="post">
		<input type="hidden" name="command" value="login">		
		<table>
			<tr>
				<td colspan="2"><fmt:message key="log_in" bundle="${text}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="user_name" bundle="${text}" /></td>
				<td><input name="login"><br /></td>
			</tr>
			<tr>
				<td><fmt:message key="password" bundle="${text}" /></td>
				<td><input name="password" type="password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="<fmt:message key="enter" bundle="${text}"/>"></td>
			</tr>
		</table>
	</form>
	<p id="message">${msg}</p>
	</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>

</html>