<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="view.Paths" %>
<html>
<head>
    <title>Add assignations Drugs</title>
</head>
<body>
<div align="center">
    <h1>Add assignations Drugs</h1>

    <form method="post" action=".${Paths.ADD_ASSIGNATIONS_DRUGS}">
        <h2>Drugs</h2>
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
</body>
</html>
