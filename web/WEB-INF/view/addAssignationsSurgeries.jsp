<%@ page import="view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <form method="post" action=".${Paths.ADD_ASSIGNATIONS_SURGERIES}">
        <table>
            <c:forEach var="i" items="${surgeriesList}">
                <tr>
                    <td> ${i.name} </td>
                    <td><input type="checkbox" name="surgeryChk_${i.id}" value="${i.id}"></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>