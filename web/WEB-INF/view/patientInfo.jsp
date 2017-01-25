<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient info</title>
</head>
<body>
<div align="center">
<h1>Patient info</h1>

<h2>${patient.lastName} ${patient.firstName} ${patient.surName}</h2>

<h3>Diagnosis history</h3>

<c:forEach var="i" items="${diagnosisHistoryList}">
    <c:if test="${i.diagnosisType == 'PRIMARY'}">
        <a href="./show_assignations?diagnosisHistoryId=${i.id}">
    </c:if>
    ${i.date} <b> ${i.diagnosis.name} </b>
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
    <c:if test="${i.diagnosisType == 'PRIMARY'}">
        </a>
    </c:if>

    <br/>
</c:forEach>

<br/>
<c:choose>
    <c:when test="${isPatientOnCure}">
        <a href="./set_diagnosis?id=${patient.id}&diagnosisType=FINAL">Виписати пацієнта</a>
    </c:when>
    <c:otherwise>
        <a href="./set_diagnosis?id=${patient.id}&diagnosisType=PRIMARY">Встановити діагноз</a>
    </c:otherwise>
</c:choose>

<br/>
<a href="./show_patients">До списку пацієнтів</a>
</div>
</body>
</html>
