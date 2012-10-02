<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<spring:url var="saveUrl" value="/productadmin/create" />
<spring:message var="title" code="addproduct.title" />
<spring:message var="heading" code="addproduct.heading" />
<tiles:insertDefinition name="product-save-form">
	<tiles:putAttribute name="title" value="${title}" />
	<tiles:putAttribute name="heading" value="${heading}" />
	<tiles:putAttribute name="saveurl" value="${saveUrl}" />
</tiles:insertDefinition>