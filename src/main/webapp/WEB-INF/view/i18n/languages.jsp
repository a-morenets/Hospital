<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="view.Parameters" %>
<%@ page import="i18n.SupportedLocale" %>
<%@ page import="view.Attributes" %>

<div align="right">
    <c:forEach items="${SupportedLocale.values()}" var="locale">
        <c:choose>
            <c:when test="${locale.locale == sessionScope[Attributes.USER_LOCALE]}">
                <b>${locale}</b>
            </c:when>
            <c:otherwise>
                <a href="?${Parameters.USER_LOCALE}=${locale.param}">${locale}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>