<%@ page import="view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <h2><fmt:message key="drugs"/></h2>
    <table>
        <c:forEach var="i" items="${assignationDrugsList}">
            <tr>
                <td>${i.drug.name}</td>
                <td>${i.numUnits} <fmt:message key="drugs.units"/></td>
                <td>${i.numTimes} <fmt:message key="drugs.times"/></td>
                <td>${i.numDays} <fmt:message key="drugs.days"/> )</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href=".${Paths.SHOW_ADD_ASSIGNATIONS_DRUGS_FORM}"><fmt:message key="drugs.add"/></a>

    <h2><fmt:message key="procedures"/></h2>
    <table>
        <c:forEach var="i" items="${assignationProceduresList}">
            <tr>
                <td>${i.procedure.name}</td>
                <td>${i.numDays} <fmt:message key="drugs.days"/></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href=".${Paths.SHOW_ADD_ASSIGNATIONS_PROCEDURES_FORM}"><fmt:message key="procedures.add"/></a>

    <h2><fmt:message key="surgeries"/></h2>
    <table>
        <c:forEach var="i" items="${assignationSurgeriesList}">
            <tr>
                <td>${i.surgery.name}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href=".${Paths.SHOW_ADD_ASSIGNATIONS_SURGERIES_FORM}"><fmt:message key="surgeries.add"/></a>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>