<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Abonnement et Joueurs</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/vues/hibernate/jspsStyle.css">
</head>
<body>
<div class="container">
    <h2>Liste des Joueurs et leurs Abonnements</h2>
    <table>
        <tr>
            <th>Nom du Joueur</th>
            <th>Type d'Abonnement</th>
        </tr>
        <c:forEach items="${requestScope.abonnements}" var="ab">
            <tr>
                <td><c:out value="${ab[0]} ${ab[1]} " /></td> 
                <td><c:out value="${ab[2]}" /></td>
            </tr>
        </c:forEach>
    </table>
    	<p>
       		<a href="${pageContext.request.contextPath}/hibernate">Retour</a>
		</p>
</div>
</body>
</html>