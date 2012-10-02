<%@ include file="commontags.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<link rel="Stylesheet" type="text/css"
	href="<spring:url value="/resources/css/themes/default/jquery.mobile-1.1.0.min.css" />" />
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.mobile-1.1.0.min.js" />"></script>
</head>
<body>
	<div data-role="page" data-theme="d">
		<div data-role="content" class="content">
			<ul data-role="listview" data-inset="true">
				<li><a href="<spring:url value="/product" />" data-ajax="false">Product</a></li>
				<li><a href="<spring:url value="/productadmin" />" data-ajax="false">Product Admin</a></li>
				<li><a href="<spring:url value="/test" />" data-ajax="false">Test</a></li>
				<li><a href="<spring:url value="/mobile" />" data-rel="dialog">Mobile</a></li>
			</ul>
		</div>
	</div>
</body>
</html>