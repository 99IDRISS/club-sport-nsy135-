<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gérer Mon Club</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vues/hibernate/style.css">
</head>
<body>
    <header>
        <div class="logo-header">
            <img src="${pageContext.request.contextPath}/assets/logo.png" alt="Club Logo" class="logo">
            <h1>Gérer Mon Club</h1>
        </div>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/hibernate?action=listerjoueurs">Liste des Joueurs</a></li>
                <li><a href="${pageContext.request.contextPath}/hibernate?action=etatInstallation">État des Installations</a></li>
                <li><a href="${pageContext.request.contextPath}/hibernate?action=abonnements">État des Abonnements</a></li>
                <li><a href="${pageContext.request.contextPath}/hibernate?action=reservations">Les Réservations</a></li>
                <li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
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
		
		<form action="${pageContext.request.contextPath}/hibernate?action=addJoueur" method="post">
    		<h3>Inscrire un Joueur</h3>

    		<label for="nom">Nom du Joueur:</label>
		    <input type="text" id="nom" name="nom" required>
		    
		    <label for="prenom">Prénom du Joueur:</label>
		    <input type="text" id="prenom" name="prenom" required><br><br>
		
		    <label for="typeAbonnement">Type Abonnement:</label>
    		<select id="typeAbonnement" name="typeAbonnement" required>
		        <option value="Forfait">Forfait</option>
		        <option value="Ticket">Ticket</option>
		    </select><br><br>
		
		    <label for="clubId">Club Id:</label>
		    <select id="clubId" name="clubId" required>
		        <option value="1">1</option>
		        <option value="2">2</option>
		    </select><br><br>
		
		    <input type="submit" value="Inscrire">
		</form>
		
		<form action="${pageContext.request.contextPath}/hibernate?action=totalChiffreAffaire" method="post">
    		<input type="submit" value="Voir le chiffre d'affaire du dernier mois">
		</form>
     </body>
     