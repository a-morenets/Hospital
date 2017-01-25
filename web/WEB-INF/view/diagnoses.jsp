<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diagnoses</title>
</head>
<body>
<div align="center">
    <h2>${patient.lastName} ${patient.firstName} ${patient.surName}</h2>

    <h2>Diagnoses:</h2>

    <form method="post" action="./add_diagnosis">

        <select name="diagnosisId" required>
            <c:forEach var="i" items="${diagnosesList}">
                <option value="${i.id}">${i.name}</option>
                <br/>
            </c:forEach>
        </select>
        <br/>

        <input type="submit">
    </form>
</div>
</body>
</html>
