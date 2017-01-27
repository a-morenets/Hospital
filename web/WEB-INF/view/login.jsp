<%@ page import="view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <form method="post" action=".${Paths.LOGIN}">
        <label><fmt:message key="email"/></label>
        <input type="text" name="login"/>
        <br/>
        <label><fmt:message key="password"/></label>
        <input type="password" name="password"/>
        <br/>
        <input type="submit" value="<fmt:message key="login"/>">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>