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
	<div id="page5" data-role="page" data-theme="${theme}">
		<div data-role="header" data-position="fixed" data-theme="${theme}">
			<a href="<spring:url value="/mobile/product" />" data-role="button" data-transition="slide" data-inline="true" data-mini="true">Home</a>
			<h1><spring:message code="checkout_complete.title" /></h1>
		</div>
		<div data-role="content" class="content">
			<h2><spring:message code="checkout.thanks" /></h2>
			<spring:message code="checkout_complete.msg" />
		</div>
	</div>
</body>
</html>