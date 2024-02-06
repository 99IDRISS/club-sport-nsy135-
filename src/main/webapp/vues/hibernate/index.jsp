<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

     <html>
     <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>My Club</title>
   	 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vues/hibernate/style.css" />
   	 
     </head>
     <body>
     
     	<header>
     	
       			<h1>Gérer Mon Club</h1>
				<nav>
       				<ul>
			             <li><a
			                     href="${pageContext.request.contextPath}/hibernate?action=listerjoueurs">Liste des Joueurs</a>
			             <li><a
			                     href="${pageContext.request.contextPath}/hibernate?action=etatInstallation">Etat des installations</a>
			             <li><a
			                     href="${pageContext.request.contextPath}/hibernate?action=abonnements">Etat des abonnements</a> 
			             <li><a
			                     href="${pageContext.request.contextPath}/hibernate?action=reservations">Les Réservations</a>   
			             
			             </li>
       				</ul>
       			</nav>
     </header>
       	<form action="${pageContext.request.contextPath}/hibernate?action=createReservation" method="post">
    		<h3>Effectuer une Réservation</h3>
    
    		<label for="joueurId">ID du Joueur:</label>
    		<input type="text" id="joueurId" name="joueurId" required><br><br>
	
    		<label for="installationId">ID de l'Installation:</label>
   			<input type="text" id="installationId" name="installationId" required><br><br>

    		<label for="dateHeure">Date et Heure:</label>
    		<input type="datetime-local" id="dateHeure" name="dateHeure" required><br><br>

    		<label for="duree">Durée (en heures):</label>
   			<input type="number" id="duree" name="duree" required><br><br>

    		<input type="submit" value="Réserver">
		</form>
		
		<form action="${pageContext.request.contextPath}/hibernate?action=calculatePayment" method="post">
			<h3>Calculer ce qui a été payé par un joueur le mois précédent</h3>
    		<label for="joueurId">ID du Joueur:</label>
    		<input type="text" id="joueurId" name="joueurId" required>
    		<input type="submit" value="Calculer Paiement">
		</form>
		
		<form action="${pageContext.request.contextPath}/hibernate?action=totalChiffreAffaire" method="post">
    		<input type="submit" value="Voir le chiffre d'affaire du dernier mois">
		</form>

     </body>