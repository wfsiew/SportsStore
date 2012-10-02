<%@ include file="../commontags.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
<link rel="Stylesheet" type="text/css"
	href="<spring:url value="/resources/css/admin.css" />" />
<link rel="Stylesheet" type="text/css"
	href="<spring:url value="/resources/css/site.css" />" />
</head>
<body>
	<h1><tiles:getAsString name="heading" /></h1>
	<tiles:importAttribute name="saveurl" />

	<form:form modelAttribute="productAttribute" method="post"
		action="${saveurl}" enctype="multipart/form-data">
		<form:label path="name" cssClass="editor-label"><spring:message code="product.name" /></form:label>
		<br />
		<form:input path="name" cssClass="text-box" />
		<br />
		<form:errors path="name" cssClass="form_error" />
		<br />
		<form:label path="description" cssClass="editor-label"><spring:message code="product.description" /></form:label>
		<br />
		<form:textarea path="description" cssClass="text-box multi-line" />
		<br />
		<form:errors path="description" cssClass="form_error" />
		<br />
		<form:label path="price" cssClass="editor-label"><spring:message code="cartline.price" /></form:label>
		<br />
		<form:input path="price" cssClass="text-box" />
		<br />
		<form:errors path="price" cssClass="form_error" />
		<br />
		<form:label path="category" cssClass="editor-label"><spring:message code="product.category" /></form:label>
		<br />
		<form:input path="category" cssClass="text-box" />
		<br />
		<form:errors path="category" cssClass="form_error" />
		<div class="editor-label"><spring:message code="product.image" /></div>
		<div class="editor-field">
			<c:if test="${productAttribute.imageData != null}">
				<spring:url var="imgUrl"
					value="/productadmin/getimage/${productAttribute.productID}" />
				<img width="150" height="150" src="${imgUrl}" />
			</c:if>
		</div>
		
		<div><spring:message code="product.upload_image" /> <form:input path="fileData" type="file" /></div>

		<input type="submit" value="<spring:message code="product.cmd.save" />" />
		<a href="<spring:url value="/productadmin" />"><spring:message code="product.cancel_form" /></a>
	</form:form>
</body>
</html>