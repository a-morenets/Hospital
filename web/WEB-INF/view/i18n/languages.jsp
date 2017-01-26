<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="view.Parameters" %>
<%@ page import="i18n.SupportedLocale" %>
<%@ page import="view.Attributes" %>

<!-- there is no need to use fmt setLocale, because native session scoped attribute already set in LocaleFilter -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${sessionScope[Attributes.BUNDLE_FILE]}"/>

<div align="right">
    <c:forEach items="${SupportedLocale.values()}" var="locale">
        <c:if test="${locale.locale == sessionScope[Attributes.USER_LOCALE]}"><b>[</c:if>
        <a href="?${Parameters.USER_LOCALE}=${locale.param}">${locale}</a>
        <c:if test="${locale.locale == sessionScope[Attributes.USER_LOCALE]}">]</b></c:if>
    </c:forEach>
</div>