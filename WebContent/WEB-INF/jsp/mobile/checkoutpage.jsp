<%@ include file="../commontags.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<spring:theme var="theme" code="name" />
	<div id="page4" data-role="page" data-theme="${theme}"
		data-add-back-btn="true">
		<div data-role="header" data-position="fixed" data-theme="${theme}">
			<a data-role="button" data-icon="arrow-l" data-rel="back" data-inline="true" data-mini="true">Back</a>
			<h1><spring:message code="checkout.title" /></h1>
		</div>
		<div data-role="content" class="content">
			<c:if test="${!empty cartempty}">
				<p class="form_error">${cartempty}</p>
			</c:if>
			<spring:url var="saveUrl" value="/mobile/cart/checkout" />
			<form:form modelAttribute="shippingDetailsAttribute" method="post"
				action="${saveUrl}">
				<h3><spring:message code="shinfo.header" /></h3>
				<div data-role="fieldcontain">
					<form:label path="name"><spring:message code="shinfo.name" /></form:label>
					<form:input path="name" data-mini="true" />
					<form:errors path="name" cssClass="form_error" />
				</div>
				
				<h3><spring:message code="shinfo.address" /></h3>
				<div data-role="fieldcontain">
					<form:label path="line1"><spring:message code="shinfo.address.line1" /></form:label>
					<form:input path="line1" data-mini="true" />
					<form:errors path="line1" cssClass="form_error" />
				</div>
				<div data-role="fieldcontain">
					<form:label path="line2"><spring:message code="shinfo.address.line2" /></form:label>
					<form:input path="line2" data-mini="true" />
				</div>
				<div data-role="fieldcontain">
					<form:label path="line3"><spring:message code="shinfo.address.line3" /></form:label>
					<form:input path="line3" data-mini="true" />
				</div>
				<div data-role="fieldcontain">
					<form:label path="city"><spring:message code="shinfo.address.city" /></form:label>
					<form:input path="city" data-mini="true" />
					<form:errors path="city" cssClass="form_error" />
				</div>
				<div data-role="fieldcontain">
					<form:label path="state"><spring:message code="shinfo.address.state" /></form:label>
					<form:input path="state" data-mini="true" />
					<form:errors path="state" cssClass="form_error" />
				</div>
				<div data-role="fieldcontain">
					<form:label path="zip"><spring:message code="shinfo.address.zip" /></form:label>
					<form:input path="zip" data-mini="true" />
				</div>
				<div data-role="fieldcontain">
					<form:label path="country"><spring:message code="shinfo.address.country" /></form:label>
					<form:input path="country" data-mini="true" />
					<form:errors path="country" cssClass="form_error" />
				</div>
				
				<h3><spring:message code="shinfo.options" /></h3>
				<div data-role="fieldcontain">
					<form:checkbox id="giftwrap" path="giftwrap" data-mini="true" />
					<form:label path="giftwrap" data-inline="true"><spring:message code="shinfo.giftwrap" /></form:label>
				</div>
				
				<button type="submit" data-role="button" data-mini="true"><spring:message code="shinfo.complete_order" /></button>
			</form:form>
		</div>
	</div>
</body>
</html>