<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient info</title>
</head>
<body>
    <h1>Patient info</h1>

    <h2>${patient.firstName} ${patient.lastName} ${patient.surName}</h2>

    <h3>Diagnosis history</h3>

    <c:forEach  var="i" items="${diagnosisHistoryList}">
        <c:if test="${i.type == PRIMARY}">
            <a href="./show_assignation?id=${i.id}">
        </c:if>
            ${i.date} ${i.diagnosisName} (${i.staffFirstName} ${i.staffLastName} ${i.staffSurName})
        <c:if test="${i.type == PRIMARY}">
            </a>
        </c:if>

        <br/>
    </c:forEach>
</body>
</html>
