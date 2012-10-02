<%@ include file="commontags.jsp" %>
<c:forEach items="${products}" var="p">
	<div class="item">

		<c:if test="${!empty p.imageData}">
			<spring:url var="imgUrl" value="/productadmin/getimage/${p.productID}" />
			<div style="float: left; margin-right: 20px">
				<img width="75" height="75" src="${imgUrl}" />
			</div>
		</c:if>

		<h3>${p.name}</h3>
		${p.description}

		<spring:url var="saveUrl" value="/cart/add/${p.productID}" />
		<spring:message var="currencyCode" code="currency.code" />
		<form:form modelAttribute="cartAttribute" action="${saveUrl}"
			method="post">
			<input id="returnUrl" name="returnUrl" type="hidden"
				value="${returnUrl}" />
			<input type="submit" value="<spring:message code="cart.add_to_cart" />" />
		</form:form>

		<h4>
			<f:formatNumber type="currency" currencyCode="${currencyCode}" value="${p.price}"></f:formatNumber>
		</h4>
	</div>
</c:forEach>

<div class="pager">${pageLinks}</div>