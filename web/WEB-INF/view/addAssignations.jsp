<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add assignations</title>
</head>
<body>
<div align="center">
    <h1>Add assignations</h1>

    <h2>Drugs</h2>
    <table>
        <c:forEach var="i" items="${drugsList}">
            <tr>
                <td> ${i.name} </td>
                <td><input type="number"></td>
                <td><input type="number"></td>
                <td><input type="number"></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Procedures</h2>
    <table>
        <c:forEach var="i" items="${proceduresList}">
            <tr>
                <td> ${i.name} </td>
                <td><input type="number"></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Surgeries</h2>
    <table>
        <c:forEach var="i" items="${surgeriesList}">
            <tr>
                <td> ${i.name} </td>
                <td><input type="checkbox"></td>
            </tr>
        </c:forEach>
    </table>

    <form method="post" action="./add_assignations">
        <input type="submit">
    </form>
</div>
</body>
</html>
