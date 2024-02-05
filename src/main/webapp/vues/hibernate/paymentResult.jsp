<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paiement resultat</title>
</head>
<body>
		<h2>Résultat du Paiement pour: ${joueurNom}</h2>
		<p>Total payé pour le mois précédent est: ${totalPaye} euro</p>
		
		<a href="${pageContext.request.contextPath}/hibernate">Retour</a>
		
</body>
</html>