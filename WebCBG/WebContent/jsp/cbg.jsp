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
<title>CBG report</title>
</head>

<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
	<h1>Welcome to CBG viewer</h1>



	Recording file link:

	<!-- this command opens the file safely but on the server machine -->
	<!-- <a href="servlet?command=openRecFile"> <c:out value="${cbg.getRecFilePath()}" />  </a>  -->
	
	 	<a href = "file://${cbg.getRecFilePath()}"><c:out value="${cbg.getRecFilePath()}" /></a> <!-- file:// needs for Google Chrome --> 

	<hr>
	
	<table>
		<tr>
			<td>

				<div align="left">
					<table>
						<tr>
							<th align="right">WaferID:</th>
							<td>${cbg.getWaferID()}</td>
						</tr>
						<tr>
							<th align="right">Grating Type:</th>
							<td>${cbg.getGratingType()}</td>
						</tr>
						<tr>
							<th align="right">Project Name:</th>
							<td>${cbg.getProject().getName()}</td>
						</tr>
						<tr>
							<th align="right">Project #:</th>
							<td>${cbg.getProject().getID()}</td>
						</tr>
						<tr>
							<th align="right">Project Wavelength, nm:</th>
							<td>${cbg.getProject().getCWL()}</td>
						</tr>
						<tr>
							<th align="right">Tested Wavelength, nm:</th>
							<td>${cbg.getTestCWL()}</td>
						</tr>
						<tr>
							<th align="right">Wafer Thickness, mm:</th>
							<td>${cbg.getWaferThickness()}</td>
						</tr>
						<tr>
							<th align="right">Wafer Dimension, WxH mm:</th>
							<td>${cbg.getWaferDimention()}</td>
						</tr>
						<tr>
							<th align="right">Recording Date:</th>
							<td>${cbg.getRecDate()}</td>
						</tr>
						<tr>
							<th align="right">Recording Dosage, J/cm^2:</th>
							<td>${cbg.getDosage()}</td>
						</tr>
						<tr>
							<th align="right">Recording Time, sec:</th>
							<td>${cbg.getRecTime()}</td>
						</tr>
						<tr>
							<th align="right">Dispersion(ps/nm)/(nm/mm):</th>
							<td>${cbg.getDispersion()}</td>
						</tr>
						<tr>
							<th align="right">M2 X:</th>
							<td>${cbg.getM2x()}</td>
						</tr>
						<tr>
							<th align="right">M2 Y:</th>
							<td>${cbg.getM2y()}</td>
						</tr>
						<tr>
							<th align="right">M2 history:</th>
							<td><a href="servlet?command=loadM2history&waferID=${cbg.getWaferID()}">Show</a></td>
						</tr>
						<!-- 
						<tr>
							<th></th>
							<td><a href="servlet?command=m2XYWindow">XY Scan</a></td>
						</tr>
						 -->
						<tr>
							<th align="right">Development:</th>
							<td><a href="${cbg.getDevelopment().getDevelopmentPath()}${cbg.getWaferID()}">Log folder</a></td>
						</tr>
						<tr>
							<th></th>
							<td>
								<c:forEach var="step" items="${cbg.getDevelopment().getDevSteps()}">
									${step} 
									<br>
								</c:forEach>							
							</td>
						</tr>
						<tr>
							<th align="right">Diffracted Beam:</th>
							<td><a href="${cbg.getDiffractedBeam().getBeamQualityReport()}">Quality report</a></td>
						</tr>
					</table>
				</div>
			</td>
			<td>
				<div>
					<c:forEach var="m2record" items="${m2Log.getRecords()}">
						<c:forEach var="m2" items="${m2record.getM2List()}">
					${m2record.getId() } ${m2.toString()} ${m2record.getSurfaceType()}  ${m2record.gratingAperture } 
					<br>
						</c:forEach>
					</c:forEach>

				</div>
			</td>
		</tr>
	</table>
	<hr>

	<h3>Recording Tools</h3>

	<table>
		<tr>
			<th>Lens</th>
			<th>Manufacture</th>
			<th>Focus</th>
		</tr>

		<c:forEach var="lens" items="${cbg.getLenses()}">
			<tr>
				<td align="center">${lens.getType()}</td>
				<td align="center">${lens.getVendor()}</td>
				<td align="center">${lens.getFocus()}</td>
			</tr>
		</c:forEach>

	</table>

	<hr>

	<table>
		<c:forEach var="entry" items="${cbg.getRecordingImages()}">

			<c:forEach var="Key" items="${entry.key}">

				<c:if test="${Key.equals('recImg')}">
					<h3>Recording Images</h3>
					<!-- <th>Start Finish</th>  -->
					<tr>
						<c:forEach var="image" items="${entry.value}">
							<td>
								<div>
									${image.getName()}<br> <img
										src="file?path=${image.getAbsolutePath() }">
								</div>
							</td>
						</c:forEach>
					</tr>
				</c:if>
			</c:forEach>

		</c:forEach>
	</table>

	<hr>


	<table>
		<c:forEach var="entry" items="${cbg.getRecordingImages()}">

			<c:forEach var="Key" items="${entry.key}">
				<c:if test="${Key.equals('recBeam')}">
					<h3>Recording Beam</h3>
					<tr>

						<c:forEach var="image" items="${entry.value}">

							<td>
								<div>
									${image.getName()} <br> <img width="400"
										src="file?path=${image.getAbsolutePath() }">
								</div>
							</td>
						</c:forEach>

					</tr>
				</c:if>

			</c:forEach>

		</c:forEach>

	</table>

	<hr>

	<div>

		<h3>Development warpage</h3>

		<table>
			<tr>

				<c:forEach var="wavefront" items="${cbg.getExposedAndDeveloped()}">
					<td>
						<div>
							${wavefront.getName()} <br> <img width="800"
								src="file?path=${wavefront.getAbsolutePath()}" />
						</div>
					</td>
				</c:forEach>

			</tr>
		</table>


	</div>

	<hr>


</body>
</html>