<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="view.Parameters" %>
<%@ page import="i18n.SupportedLocale" %>

<%@ page import="controller.HospitalConfig" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         errorPage="/WEB-INF/view/errorPage/errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hospital</title>
</head>
<body style="background: #b8d5dc">

<div align="center">
    <br>
    <div class="container" align="center">

        <%@ include file="/WEB-INF/view/i18n/languages.jsp" %>
        <br>
        <div align="center">
            <img src="${pageContext.request.contextPath}/img/drHouse.jpg" height="100px"><br>
            <h1><fmt:message key="title.home"/></h1>
        </div>

