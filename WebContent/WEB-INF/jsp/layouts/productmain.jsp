<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
<link rel="Stylesheet" type="text/css"
	href="<spring:url value="/resources/css/site.css" />" />
</head>
<body>
	<div id="header">
		<div id="lang">
			<a href="?lang=en" title="English">EN</a> |
			<a href="?lang=ja" title="Japanese">JA</a>
		</div>
		<tiles:insertAttribute name="header" />
		<div class="title"><tiles:getAsString name="header-title" /></div>
	</div>
	<div id="categories">
		<tiles:insertAttribute name="categories" />
	</div>
	<div id="content">
		<tiles:insertAttribute name="content" />
	</div>
</body>
</html>