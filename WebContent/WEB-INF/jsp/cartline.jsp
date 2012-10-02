<%@ include file="commontags.jsp" %>
<spring:message var="currencyCode" code="currency.code" />
<h2><spring:message code="cart.msg" /></h2>
<table width="90%" align="center">
	<thead>
		<tr>
			<th align="center"><spring:message code="cartline.quantity" /></th>
			<th align="left"><spring:message code="cartline.item" /></th>
			<th align="right"><spring:message code="cartline.price" /></th>
			<th align="right"><spring:message code="cartline.subtotal" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cart.getLines()}" var="line">
			<tr>
				<td align="center">${line.quantity}</td>
				<td align="left">${line.product.name}</td>
				<td align="right"><f:formatNumber type="currency"
						currencyCode="${currencyCode}" value="${line.product.price}"></f:formatNumber></td>
				<td align="right">
					<f:formatNumber type="currency" currencyCode="${currencyCode}" value="${line.quantity * line.product.price}"></f:formatNumber>
				</td>
				<td>
					<spring:url var="saveUrl" value="/cart/remove/${line.product.productID}" />
					<form:form modelAttribute="cartAttribute" action="${saveUrl}" method="post">
						<input id="returnUrl" name="returnUrl" type="hidden" value="${returnUrl}" />
						<input type="submit" value="<spring:message code="cartline.remove" />" class="actionButtons" />
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="3" align="right"><spring:message code="cartline.total" /></td>
			<td align="right">
				<f:formatNumber type="currency" currencyCode="${currencyCode}" value="${cart.computeTotalValue()}"></f:formatNumber>
			</td>
		</tr>
	</tfoot>
</table>
<p align="center" class="actionButtons">
	<a href="${returnUrl}"><spring:message code="cartline.continue_shopping" /></a>
	<a href="<spring:url value="/cart/checkout" />"><spring:message code="cartline.checkoutnow" /></a>
</p>