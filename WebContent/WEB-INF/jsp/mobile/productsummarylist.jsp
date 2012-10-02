<%@ include file="../commontags.jsp"%>
<c:forEach items="${products}" var="p">
	<li>
		<spring:url var="saveUrl" value="/mobile/cart/add/${p.productID}" />
		<spring:message var="currencyCode" code="currency.code" />
		<a href="#">
			<c:if test="${!empty p.imageData}">
				<spring:url var="imgUrl"
					value="/productadmin/getimage/${p.productID}" />
				<div style="float: left; margin-right: 20px">
					<img width="45" height="45" src="${imgUrl}" />
				</div>
			</c:if>
			<h3>${p.name}</h3>
			<p>${p.description}</p>
			<p><f:formatNumber type="currency" currencyCode="${currencyCode}" value="${p.price}"></f:formatNumber></p>
		</a>
		<a href="${saveUrl}"><spring:message code="cart.add_to_cart2" /></a>
	</li>
</c:forEach>