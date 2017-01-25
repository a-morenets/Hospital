<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diagnoses</title>
</head>
<body>
    <h1>Diagnoses</h1>

    <c:forEach  var="i" items="${diagnosesList}">
        ${i.name}
        <br/>
    </c:forEach>
    <br/>

    <form method="post" action="./add_diagnosis">
        <input type="submit">
    </form>
</body>
</html>
