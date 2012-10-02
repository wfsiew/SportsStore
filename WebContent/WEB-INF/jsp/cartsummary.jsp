<%@ include file="commontags.jsp" %>
<spring:message var="currencyCode" code="currency.code" />
<div id="cart">
	<span class="caption">
		<b><spring:message code="cart.msg" />:</b>
		${cart.getTotalQuantity()} <spring:message code="items" />,
		<f:formatNumber type="currency" currencyCode="${currencyCode}" value="${cart.computeTotalValue()}"></f:formatNumber>
	</span>
	<a href="<spring:url value="/cart/checkout" />"><spring:message code="checkout" /></a>
</div>