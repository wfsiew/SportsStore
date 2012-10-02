<%@ include file="commontags.jsp" %>
<h2><spring:message code="checkout.header" /></h2>
<spring:message code="checkout.msg" />
<spring:url var="saveUrl" value="/cart/checkout" />
<c:if test="${!empty cartempty}">
	<p class="form_error">${cartempty}</p>
</c:if>
<form:form modelAttribute="shippingDetailsAttribute" method="post"
	action="${saveUrl}">
	<h3><spring:message code="shinfo.header" /></h3>
	<div>
		<spring:message code="shinfo.name" />
		<form:input path="name" />
		<form:errors path="name" cssClass="form_error" />
	</div>
	
	<h3><spring:message code="shinfo.address" /></h3>
	<div>
		<spring:message code="shinfo.address.line1" /> 
		<form:input path="line1" />
		<form:errors path="line1" cssClass="form_error" />
	</div>
	<div>
		<spring:message code="shinfo.address.line2" />
		<form:input path="line2" />
	</div>
	<div>
		<spring:message code="shinfo.address.line3" />
		<form:input path="line3" />
	</div>
	<div>
		<spring:message code="shinfo.address.city" />  
		<form:input path="city" />
		<form:errors path="city" cssClass="form_error" />
	</div>
	<div>
		<spring:message code="shinfo.address.state" />  
		<form:input path="state" />
		<form:errors path="state" cssClass="form_error" />
	</div>
	<div>
		<spring:message code="shinfo.address.zip" />
		<form:input path="zip" />
	</div>
	<div>
		<spring:message code="shinfo.address.country" /> 
		<form:input path="country" />
		<form:errors path="country" cssClass="form_error" />
	</div>
	
	<h3><spring:message code="shinfo.options" /></h3>
	<label>
		<form:checkbox path="giftwrap" />
		<form:label path="giftwrap"><spring:message code="shinfo.giftwrap" /></form:label>
	</label>
	
	<p align="center">
		<input type="submit" value="<spring:message code="shinfo.complete_order" />" class="actionButtons" />
	</p>
</form:form>