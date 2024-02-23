<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes installations</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/vues/hibernate/jspsStyle.css">
</head>
<body>
<div class="container">
    <h1>L'état des installations est comme suivant :</h1>

    <table>
        <tr>
            <th>installation Id</th>
            <th>Type</th>
            <th>Date de mise en service</th>
            <th>État Général</th>
            <th>Details</th>
        </tr>
        <c:forEach items="${requestScope.installations}" var="i">
            <tr>
                <td> <c:out value="${i.id }"/></td>
                <td><c:out value="${i.typeInstallation}"/></td>
                <td><c:out value="${i.dateMiseEnService}"/></td>
                <td><c:out value="${i.etatGeneral}"/></td>
                <td>
                	 <c:choose> 
                        <c:when test="${i.typeInstallation == 'Tennis'}">
                            Surface : <c:out value="${i.surface}"/>
                        </c:when>
                        <c:when test="${i.typeInstallation == 'Squash'}">
                            Vitre : <c:out value="${i.vitre}"/><br>
                            Date Remplacement Parquet : <c:out value="${i.dateRemplacementParquet}"/>
                        </c:when>
                        <c:when test="${i.typeInstallation == 'Badminton'}">
                            Date Achat Filets : <c:out value="${i.dateAchatFilets}"/><br> 
                            Date Achat Poteaux : <c:out value="${i.dateAchatPoteaux}"/>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>L'évaluation de l'état général se fait sur une échelle de 1 à 5 pour chaque salle, où 1 indique un mauvais état et 5 un bon état.</p>

    <p>
        <a href="${pageContext.request.contextPath}/hibernate">Retour</a>
    </p>
</div>
</body>
</html>
