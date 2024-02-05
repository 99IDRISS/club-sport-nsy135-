<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes installations</title>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 10px;
  text-align: left;
}
</style>
</head>
<body>
	<h1>L'état des installations est comme suivant :</h1>

	<table>
  		<tr>
    		<th>Type</th>
    		<th>Date de mise en service</th>
    		<th>État Général</th>
  		</tr>
  		<c:forEach items="${requestScope.installations}" var="i">
    		<tr>
      			<td><c:out value="${i.typeInstallation}" /></td>
      			<td><c:out value="${i.dateMiseEnService}" /></td>
      			<td><c:out value="${i.etatGeneral}" /></td>
    		</tr>
  		</c:forEach>
	</table>

	<p>
		<a href="${pageContext.request.contextPath}/hibernate">Accueil</a>
	</p>

</body>
</html>
