<%@ include file="commontags.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="product.admin.title" /></title>
<link rel="Stylesheet" type="text/css"
	href="<spring:url value="/resources/css/admin.css" />" />
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery-1.7.1.min.js" />"></script>
</head>
<body>
	<spring:message var="currencyCode" code="currency.code" />
	<c:if test="${!empty message}">
		<div class="Message">${message}</div>
	</c:if>
	<c:if test="${param.message != null}">
		<div class="Message">${param.message}</div>
	</c:if>
	<h1><spring:message code="product.admin.header" /></h1>
	<table class="Grid">
		<tr>
			<th><spring:message code="product.id" /></th>
			<th><spring:message code="product.name" /></th>
			<th class="NumericCol"><spring:message code="cartline.price" /></th>
			<th><spring:message code="product.actions" /></th>
		</tr>
		<c:forEach items="${products}" var="p">
		<tr>
			<td>${p.productID}</td>
			<td><a href="<spring:url value="/productadmin/edit/${p.productID}" />">${p.name}</a></td>
			<td class="NumericCol">
				<f:formatNumber type="currency" currencyCode="${currencyCode}" value="${p.price}"></f:formatNumber>
			</td>
			<td><a href="<spring:url value="/productadmin/delete/${p.productID}" />"><spring:message code="product.cmd.delete" /></a></td>
		</tr>
		</c:forEach>
	</table>
	<p>
		<a href="<spring:url value="/productadmin/create" />"><spring:message code="product.cmd.add" /></a>
		<a href="<spring:url value="/j_spring_security_logout" />"><spring:message code="logout" /></a>
	</p>
</body>
</html>