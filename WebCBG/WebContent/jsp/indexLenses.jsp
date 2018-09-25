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

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/show.js" type="text/javascript"></script>


<title>Lenses Stock</title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>

	<form name="lensForm" action="servlet" method="post">
		<h1>Lens's Stock</h1>

		<h3>Please, be patient since the search may take several minutes!
		</h3>

		<p>
			Recorded after (yyyy-mm-dd) <input name="modified" value="2017-01-01" />
		</p>


		<input type="hidden" name="command" value="lensSearch"> <select
			name="lensSelect">
			<c:forEach var="lens" items="${lenses}">
				<!--  <option value="${item.key}" ${item.key == selectedDept ? 'selected="selected"' : ''}>${item.value}</option>  -->
				<option value="${lens.id }">${lens.id }</option>
			</c:forEach>
		</select> <input type="submit" id="searchButton" value="<fmt:message key="search" bundle="${text}"/>" />
	</form>

	<div class="loggif">
		 <img src="images/ajax-loader.gif"> 	
	</div>
	
</body>

</html>