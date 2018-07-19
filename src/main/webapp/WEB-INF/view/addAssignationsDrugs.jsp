<%@ page import="view.Paths" %>
<%@ page import="view.Parameters" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <form method="post" action=".${Paths.ADD_ASSIGNATIONS_DRUGS}">
        <input type="hidden" name="${Parameters.DIAGNOSIS_HISTORY_ID}" value="${diagnosisHistoryId}">
        <table>
            <c:forEach var="i" items="${drugsList}">
                <tr>
                    <td> ${i.name} </td>
                    <td><input type="number" name="drugNumUnits_${i.id}"></td>
                    <td><input type="number" name="drugNumTimes_${i.id}"></td>
                    <td><input type="number" name="drugNumDays_${i.id}"></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>
