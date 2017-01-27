<%@ page import="view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <h2>${patient.lastName} ${patient.firstName} ${patient.surName}</h2>
    <form method="post" action=".${Paths.ADD_DIAGNOSIS}">

        <select name="diagnosisId" required>
            <c:forEach var="i" items="${diagnosesList}">
                <option value="${i.id}">${i.name}</option>
                <br/>
            </c:forEach>
        </select>
        <input type="submit">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>