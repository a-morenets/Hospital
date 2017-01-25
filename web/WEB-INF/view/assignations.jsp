<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assignations</title>
</head>
<body>
    <h1>Assignations</h1>

    <h2>Drugs</h2>

    <c:forEach  var="i" items="${assignationDrugsList}">
        ${i.drug.name} ( ${i.numUnits} units ${i.numTimes} times/day ${i.numDays} days )
        <br/>
    </c:forEach>
    <br/>

    <h2>Procedures</h2>

    <c:forEach  var="i" items="${assignationProceduresList}">
        ${i.procedure.name} ( ${i.numDays} days )
        <br/>
    </c:forEach>
    <br/>

    <h2>Surgeries</h2>

    <c:forEach  var="i" items="${assignationSurgeriesList}">
        ${i.surgery.name}
        <br/>
    </c:forEach>
    <br/>

    <br/>
    <a href="./add_assignations_form">Add assignations</a>
</body>
</html>
