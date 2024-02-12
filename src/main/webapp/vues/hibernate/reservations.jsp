<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Réservations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vues/hibernate/jspsStyle.css">
</head>
<body>
    <div class="container">
        <h2>Liste des réservations</h2>
        <table>
            <tr>
                <th>Reservé par :</th>
                <th>Date et heure</th>
                <th>Type d'installation</th>
                <th>Durée en heures</th>
            </tr>
            <c:forEach items="${requestScope.reservations}" var="re">
                <tr>
                    <td><c:out value="${re[0]} ${re[1]} " /></td> 
                    <td><c:out value="${re[2]}" /></td>
                    <td><c:out value="${re[4]} ID: ${re[5]}" /></td>
                    <td><c:out value="${re[3]} h" /></td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/hibernate">Retour</a>
    </div>
</body>
</html>

