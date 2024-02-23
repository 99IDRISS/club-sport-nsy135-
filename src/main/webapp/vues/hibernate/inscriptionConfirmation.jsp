<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reservation Confirmation</title>
</head>
<body>
    <h1>Reservation Status</h1>
    <p>${requestScope.confirmationMessage}</p>
    <a href= "${pageContext.request.contextPath}/hibernate">Accueil</a>
</body>
</html>
