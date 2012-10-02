<%@ include file="commontags.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="login.title" /></title>
<link rel="Stylesheet" type="text/css"
	href="<spring:url value="/resources/css/admin.css" />" />
</head>
<body>
	<h1><spring:message code="login.header" /></h1>

	<p><spring:message code="login.msg" /></p>

	<c:if test="${not empty error}">
		<div class="errorblock">${errormsg}</div>
	</c:if>

	<spring:url var="loginUrl" value="/j_spring_security_check" />
	<form name="f" method="post" action="${loginUrl}">
		<label class="editor-label"><spring:message code="login.username" /></label>
		<br />
		<input type="text" name="j_username" class="text-box" />
		<br />
		<label class="editor-label"><spring:message code="login.password" /></label>
		<br />
		<input type="password" name="j_password" class="text-box" />
		<br />
		<p>
			<input name="submit" type="submit" value="<spring:message code="login.submit" />" />
		</p>
		<p>
			<a href="?lang=en" title="English">EN</a> |
			<a href="?lang=ja" title="Japanese">JA</a>
		</p>
	</form>
</body>
</html>