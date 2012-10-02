<%@ include file="../commontags.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<spring:theme var="theme" code="name" />
	<div id="page2" data-role="page" data-theme="${theme}">
		<div data-role="header" data-position="fixed" data-theme="${theme}">
			<a href="<spring:url value="/mobile/product" />" data-role="button" data-transition="slide">Home</a>
			<h1>${selectedCategory}</h1>
			<a id="cmdrefresh" href="#" data-role="button"><spring:message code="refresh" /></a>
		</div>
		<div id="page2ct" data-role="content" class="content">
			<ul data-role="listview" data-split-icon="plus" data-split-theme="${theme}">
			<%@ include file="productsummarylist.jsp"%>
			</ul>
		</div>
		<input id="category" type="hidden" value="${selectedCategory}" />
		<input id="returnurl" type="hidden" value="${returnUrl}" />
	</div>
</body>
</html>