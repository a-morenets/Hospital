<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="view.Paths" %>
<html>
<head>
    <title>Add assignations Procedures</title>
</head>
<body>
<div align="center">
    <h1>Add assignations Procedures</h1>

    <form method="post" action=".${Paths.ADD_ASSIGNATIONS_PROCEDURES}">
        <h2>Procedures</h2>
        <table>
            <c:forEach var="i" items="${proceduresList}">
                <tr>
                    <td> ${i.name} </td>
                    <td><input type="number" name="procedureNumDays_${i.id}"></td>
                </tr>
            </c:forEach>
        </table>

        <input type="submit">
    </form>
</div>
</body>
</html>
