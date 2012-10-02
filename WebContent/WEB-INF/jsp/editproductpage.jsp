<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<spring:url var="saveUrl"
	value="/productadmin/edit/${productAttribute.productID}" />
<spring:message var="title" code="editproduct.title" />
<spring:message var="heading" code="editproduct.heading" />
<tiles:insertDefinition name="product-save-form">
	<tiles:putAttribute name="title" value="${title} ${productAttribute.name}" />
	<tiles:putAttribute name="heading" value="${heading} ${productAttribute.name}" />
	<tiles:putAttribute name="saveurl" value="${saveUrl}" />
</tiles:insertDefinition>