<%@ include file="../commontags.jsp" %>
<spring:message var="currencyCode" code="currency.code" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<spring:theme var="theme" code="name" />
	<div id="page3" data-role="page" data-theme="${theme}">
		<div data-role="header" data-position="fixed" data-theme="${theme}">
			<a href="${returnUrl}" data-role="button" data-transition="slide" data-inline="true" data-mini="true"><spring:message code="cartline.continue_shopping" /></a>
			<h1><spring:message code="cart.title" /></h1>
		</div>
		<div data-role="content" class="content">
			<ul data-role="listview" data-split-icon="delete" data-split-theme="${theme}">
			<c:forEach items="${cart.getLines()}" var="line">
				<li>
					<spring:url var="saveUrl" value="/mobile/cart/remove/${line.product.productID}" />
					<a href="#">
						<p>
							<b>[${line.quantity}]</b> ${line.product.name} <f:formatNumber type="currency"
								currencyCode="${currencyCode}" value="${line.product.price}"></f:formatNumber>
							<b>[<f:formatNumber type="currency" currencyCode="${currencyCode}" value="${line.quantity * line.product.price}"></f:formatNumber>]</b>
						</p>
					</a>
					<a href="${saveUrl}"><spring:message code="cartline.remove" /></a>
				</li>
			</c:forEach>
			</ul>
		</div>
		<div data-role="footer" data-position="fixed" data-theme="${theme}">
			<h1>
				<spring:message code="cartline.total" />
				<f:formatNumber type="currency" currencyCode="${currencyCode}" value="${cart.computeTotalValue()}"></f:formatNumber>
				<a href="<spring:url value="/mobile/cart/checkout" />" data-role="button" data-inline="true" data-mini="true"><spring:message code="cartline.checkoutnow" /></a>
			</h1>
		</div>
	</div>
</body>
</html>