<%@ include file="commontags.jsp" %>
<a href="<spring:url value="/product" />">Home</a>
<a href="<spring:url value="/cart" />"><spring:message code="cart.shoppingcart" /></a>
<c:forEach items="${categories}" var="c">
	<c:if test="${empty selectedCategory}">
		<a href="<spring:url value="/product/${c}" />">${c}</a>
	</c:if>
	<c:if test="${!empty selectedCategory}">
		<c:if test="${c.equalsIgnoreCase(selectedCategory)}">
			<a href="<spring:url value="/product/${c}" />" class="selected">${c}</a>
		</c:if>
		<c:if test="${c != selectedCategory}">
			<a href="<spring:url value="/product/${c}" />">${c}</a>
		</c:if>
	</c:if>
</c:forEach>