<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/xml_rt" prefix="x"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/resources/xml/catalog.xml" var="xmlurl" />
	<c:import url="/resources/xml/catalog.xsl" var="xslurl" />
	<x:transform xml="${xmlurl}" xslt="${xslurl}" />
<%-- 		<x:parse xml="${xmlurl}" var="doc" /> --%>
<!-- 		<table border="1"> -->
<!-- 			<thead> -->
<!-- 				<tr> -->
<!-- 					<th>Title</th> -->
<!-- 					<th>Artist</th> -->
<!-- 					<th>Country</th> -->
<!-- 					<th>Company</th> -->
<!-- 					<th>Price</th> -->
<!-- 				</tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 				<x:forEach select="$doc/catalog/cd" var="item"> --%>
<!-- 				<tr> -->
<%-- 					<td><x:out select="title" /></td> --%>
<%-- 					<td><x:out select="artist" /></td> --%>
<%-- 					<td><x:out select="country" /></td> --%>
<%-- 					<td><x:out select="company" /></td> --%>
<%-- 					<td><x:out select="price" /></td> --%>
<!-- 				</tr> -->
<%-- 				</x:forEach> --%>
<!-- 			</tbody> -->
<!-- 		</table> -->
</body>
</html>