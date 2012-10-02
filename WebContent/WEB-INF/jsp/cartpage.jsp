<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<spring:message var="carttitle" code="cart.title" />
<spring:message var="headertitle" code="product.header.title" />
<tiles:insertDefinition name="template-cartpage">
	<tiles:putAttribute name="title" value="${carttitle}" />
	<tiles:putAttribute name="header-title" value="${headertitle}" />
</tiles:insertDefinition>