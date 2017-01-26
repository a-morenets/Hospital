<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="view.Paths" %>
<html>
<head>
    <title>Add assignations Surgeries</title>
</head>
<body>
<div align="center">
    <h1>Add assignations Surgeries</h1>

    <form method="post" action=".${Paths.ADD_ASSIGNATIONS_SURGERIES}">
        <h2>Surgeries</h2>
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
</body>
</html>
