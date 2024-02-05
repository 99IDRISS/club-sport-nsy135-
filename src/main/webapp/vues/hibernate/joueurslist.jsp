<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>les joueurs du club</title>
</head>
<body>
	<h1>les Joueurs du club sont :</h1>

		<ul>
  			<c:forEach items="${requestScope.joueurs}" var="j">
    			<li><c:out value="${j.nom} ${j.prenom}"/></li>
  			</c:forEach>
		</ul>

		<p>
       		<a href="${pageContext.request.contextPath}/hibernate">Accueil</a>
		</p>

</body>
</html>