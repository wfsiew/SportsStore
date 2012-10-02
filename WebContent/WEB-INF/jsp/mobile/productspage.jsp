<%@ include file="../commontags.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:message var="producttitle" code="product.title" />
<title>${producttitle}</title>
<link rel="Stylesheet" type="text/css"
	href="<spring:url value="/resources/css/themes/default/jquery.mobile-1.1.1.min.css" />" />
<link rel="Stylesheet" type="text/css"
	href="<spring:url value="/resources/css/mobile.css" />" />
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.mobile-1.1.1.min.js" />"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/mobile/productsummary.js" />"></script>
</head>
<body>
	<spring:message var="headertitle" code="product.header.title" />
	<spring:message var="currencyCode" code="currency.code" />
	<spring:theme var="theme" code="name" />
	<div id="page1" data-role="page" data-theme="${theme}">
		<div data-role="header" data-position="fixed" data-theme="${theme}">
			<a href="<spring:url value="/mobile/cart/checkout" />" data-role="button" data-transition="slide"><spring:message code="checkout" /></a>
			<h1>${headertitle}</h1>
			<a href="#options" data-role="button" data-icon="gear" data-rel="dialog" data-transition="pop"><spring:message code="options" /></a>
		</div>
		<div data-role="content" class="content">
			<ul data-role="listview" data-inset="true">
				<li><a href="<spring:url value="/mobile/cart" />" data-transition="slide"><spring:message code="cart.shoppingcart" /></a></li>
				<li><a href="<spring:url value="/mobile/product/index/1" />" data-transition="slide"><spring:message code="product.allcategories" /></a></li>
			<c:forEach items="${categories}" var="c">
				<li><a href="<spring:url value="/mobile/product/${c}" />" data-transition="slide">${c}</a></li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div id="options" data-role="page" data-theme="${theme}">
		<div data-role="header" data-position="fixed" data-theme="${theme}">
			<h3><spring:message code="options" /></h3>
		</div>
		<div data-role="content">
			<div data-role="collapsible-set">
				<div data-role="collapsible" data-collapsed="false">
					<h3><spring:message code="language" /></h3>
					<div data-role="controlgroup" data-type="horizontal">
						<a href="?lang=en" data-role="button" data-inline="true" data-mini="true" data-ajax="false">EN</a>
						<a href="?lang=ja" data-role="button" data-inline="true" data-mini="true" data-ajax="false">JA</a>
					</div>
				</div>
				<div data-role="collapsible">
					<h3><spring:message code="theme" /></h3>
					<div data-role="controlgroup" data-type="horizontal">
						<a href="?theme=a" data-role="button" data-inline="true" data-mini="true" data-theme="a" data-ajax="false">Swatch A</a>
						<a href="?theme=b" data-role="button" data-inline="true" data-mini="true" data-theme="b" data-ajax="false">Swatch B</a>
						<a href="?theme=c" data-role="button" data-inline="true" data-mini="true" data-theme="c" data-ajax="false">Swatch C</a>
						<a href="?theme=d" data-role="button" data-inline="true" data-mini="true" data-theme="d" data-ajax="false">Swatch D</a>
						<a href="?theme=e" data-role="button" data-inline="true" data-mini="true" data-theme="e" data-ajax="false">Swatch E</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>