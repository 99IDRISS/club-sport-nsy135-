<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes installations</title>
<style>
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}
</style>
</head>
<body>
	<h1>L'état des installations est comme suivant :</h1>

	<table>
  		<tr>
  			<th>installation Id</th>
    		<th>Type</th>
    		<th>Date de mise en service</th>
    		<th>État Général</th>
  		</tr>
  		<c:forEach items="${requestScope.installations}" var="i">
    		<tr>
    			<td> <c:out value="${i.id }"></c:out></td>
      			<td><c:out value="${i.typeInstallation}" /></td>
      			<td><c:out value="${i.dateMiseEnService}" /></td>
      			<td><c:out value="${i.etatGeneral}" /></td>
    		</tr>
  		</c:forEach>
	</table>
	<p>L'évaluation de l'état général se fait sur une échelle de 1 à 5 pour chaque salle, où 1 indique un mauvais état et 5 un bon état.</p>

	<p>
		<a href="${pageContext.request.contextPath}/hibernate">Retour</a>
	</p>

</body>
</html>
