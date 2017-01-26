<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="view.Paths" %>
<html>
<head>
    <title>Assignations</title>
</head>
<body>
<div align="center">
    <h1>Assignations</h1>

    <h2>Drugs</h2>

    <c:forEach var="i" items="${assignationDrugsList}">
        ${i.drug.name} ( ${i.numUnits} units ${i.numTimes} times/day ${i.numDays} days )
        <br/>
    </c:forEach>
    <br/>

    <br/>
    <a href=".${Paths.SHOW_ADD_ASSIGNATIONS_DRUGS_FORM}">Add assignations drugs</a>

    <h2>Procedures</h2>

    <c:forEach var="i" items="${assignationProceduresList}">
        ${i.procedure.name} ( ${i.numDays} days )
        <br/>
    </c:forEach>
    <br/>

    <br/>
    <a href=".${Paths.SHOW_ADD_ASSIGNATIONS_PROCEDURES_FORM}">Add assignations procedures</a>

    <h2>Surgeries</h2>

    <c:forEach var="i" items="${assignationSurgeriesList}">
        ${i.surgery.name}
        <br/>
    </c:forEach>
    <br/>

    <br/>
    <a href=".${Paths.SHOW_ADD_ASSIGNATIONS_SURGERIES_FORM}">Add assignations surgeries</a>
</div>
</body>
</html>
