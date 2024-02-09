<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>les joueurs du club</title>
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
	<h1>les Joueurs du club sont :</h1>
	
	<table>
  		<tr>
    		<th>Joueur ID</th>
    		<th>Nom</th>
    		<th>Prenom</th>
  		</tr>
  		<c:forEach items="${requestScope.joueurs}" var="j">
    		<tr>
      			<td><c:out value="${j.id}" /></td>
      			<td><c:out value="${j.nom}" /></td>
      			<td><c:out value="${j.prenom}" /></td>
    		</tr>
  		</c:forEach>
	</table>


		<p>
       		<a href="${pageContext.request.contextPath}/hibernate">Retour</a>
		</p>

</body>
</html>