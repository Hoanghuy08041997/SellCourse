<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Active Course</title>
</head>
<body>
    <h1>Active Course</h1>
    <form action="activeCourse" method="post">
        <label for="activationCode">Activation Code:</label>
        <input type="text" id="activationCode" name="activationCode" required>
        <button type="submit">Activate</button>
    </form>
    <c:if test="${not empty mes}">
        <p>${mes}</p>
    </c:if>
</body>
</html>
