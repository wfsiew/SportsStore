<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Mobile Test Page</title>
</head>
<body>
	<div data-role="page" data-theme="d">
		<div data-role="header" data-theme="d">
			<h3>Spring Mobile Test Page</h3>
		</div>
		<div data-role="content" class="content">
			<span>You are using ${device}</span>
			<spring:url var="url" value="/mobile/home" />
			<ul data-role="listview" data-inset="true" data-divider-theme="d">
				<li data-role="list-divider">Preferred Site</li>
				<li><a href="${url}?site_preference=normal" data-ajax="false">Normal</a></li>
				<li><a href="${url}?site_preference=mobile" data-ajax="false">Mobile</a></li>
			</ul>
		</div>
	</div>
</body>
</html>