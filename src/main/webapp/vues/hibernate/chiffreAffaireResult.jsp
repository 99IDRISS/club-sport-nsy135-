<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chiffre d'affaire </title>
</head>
<body>
		<h2>Chiffre d'affaire du dernier mois</h2>
		<p>Le chiffre d'affaire pour le mois précédent est : ${chiffreAffaire}</p>
		<a href="${pageContext.request.contextPath}/hibernate">Retour</a>

</body>
</html>