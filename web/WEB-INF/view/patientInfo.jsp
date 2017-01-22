<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient info</title>
</head>
<body>
    <h1>Patient info</h1>

    <h2>${patient.lastName} ${patient.firstName} ${patient.surName}</h2>

    <h3>Diagnosis history</h3>

    <c:forEach  var="i" items="${diagnosisHistoryList}">
        <c:if test="${i.type == 'PRIMARY'}">
            <a href="./show_assignations?id=${i.id}">
        </c:if>
            ${i.date} ${i.diagnosis.name}
            (
        <c:choose>
            <c:when test="${i.staff.role == 'DOCTOR'}">
                Лікар:
            </c:when>
            <c:otherwise>
                Медсестра:
            </c:otherwise>
        </c:choose>

        ${i.staff.lastName} ${i.staff.firstName} ${i.staff.surName}
        )
        <c:if test="${i.type == 'PRIMARY'}">
            </a>
        </c:if>

        <br/>
    </c:forEach>

    <br/>

    <a href="./set_diagnosis?id=${patient.id}&type=PRIMARY">Встановити діагноз</a>
    <a href="./set_diagnosis?id=${patient.id}&type=FINAL">Виписати пацієнта</a>
    <a href="./show_patients">До списку пацієнтів</a>
</body>
</html>
