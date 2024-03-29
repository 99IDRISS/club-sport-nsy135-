package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.*;
import modeles.SABAH.*;


/**
 * Servlet implementation class Hibernate
 */
@WebServlet("/hibernate")
public class Hibernate extends HttpServlet {
		private static final String SERVER="localhost", BD="SABAH",
	            LOGIN="idriss", PASSWORD="Strong123!", VUES="/vues/hibernate/";
	
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    	// On devrait récupérer l'action requise par l'utilisateur
    	String action = request.getParameter("action");
    	// Notre objet modèle: accès à MySQL
    	Myclub club;					
    	// La vue par défaut
    	String maVue = VUES + "index.jsp";

    	try {
            	if (action == null) {
                    // Rien à faire
            	} else if (action.equals("connexion")) {
            	// Action + vue test de connexion
            		club = new Myclub();
            		maVue = VUES + "connexion.jsp";
            	}else if (action.equals("listerjoueurs")) {
            		club = new Myclub();
            		List<Joueur> joueurs = club.getListJoueurs();
            		request.setAttribute("joueurs", joueurs);
            		maVue = VUES + "joueurslist.jsp";
            	}else if (action.equals("etatInstallation")){
					club = new Myclub();
					List<Map<String, Object>> installations = club.getDetailInstallations();
					request.setAttribute("installations", installations);
					maVue = VUES + "etatInstallation.jsp";
				}else if (action.equals("abonnements")) {
					club = new Myclub();
					List<Object[]> abonnements = club.getJoueurAbonnement();
					request.setAttribute("abonnements", abonnements);
					maVue = VUES + "joueurAbonnement.jsp";
				}else if (action.equals("reservations")) {
					club = new Myclub();
					List<Object[]> reservations = club.getReservations();
					request.setAttribute("reservations", reservations);
					maVue = VUES + "reservations.jsp";
				}
    			}catch (Exception e) {
    				request.setAttribute("message", e.getLocalizedMessage());
            		maVue = VUES + "exception.jsp";
            		
    	}
         // On transmet à la vue
            RequestDispatcher dispatcher = getServletContext()
                            .getRequestDispatcher(maVue);
            dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String maVue =  VUES + "index.jsp";

        if (action != null && action.equals("createReservation")) {
            try {
                Myclub club = new Myclub();

                // Retrieve and parse form data
                Integer joueurId = Integer.parseInt(request.getParameter("joueurId"));
                Integer installationId = Integer.parseInt(request.getParameter("installationId"));
                String dateHeureStr = request.getParameter("dateHeure");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date dateHeure = formatter.parse(dateHeureStr);
                Integer duree = Integer.parseInt(request.getParameter("duree"));

                // Create a new reservation
                Reservation reservation = new Reservation();
                // Set properties (assuming appropriate setter methods)
                reservation.setJoueur(club.findJoueurById(joueurId));
                reservation.setInstallation(club.findInstallationById(installationId));
                reservation.setDateHeure(dateHeure);
                reservation.setDuree(duree);

                // Save the reservation
                if (!club.saveReservation(reservation)) {
                	 request.setAttribute("errorMessage", "Une erreur s'est produite lors de la réservation.");
         	         maVue = VUES + "erreurReservation.jsp";
                	
                }else {
                	  // Set confirmation message
                    request.setAttribute("confirmationMessage", "Votre réservation a été enregistrée !");
                    maVue = VUES + "reservationConfirmation.jsp";
                	
                }
                
            } catch (Exception e) {
            	e.printStackTrace();
            	}
            
            
        }else if (action.equals("calculatePayment")) {
            Integer joueurId = Integer.parseInt(request.getParameter("joueurId"));
            Myclub club = new Myclub();
            BigDecimal totalPaye = club.calculerTotalpayeMoisPrec(joueurId);
            request.setAttribute("totalPaye", totalPaye);
            Joueur joueur = club.findJoueurById(joueurId);
            request.setAttribute("joueurNom", joueur.getNom() + " " + joueur.getPrenom());
            maVue = VUES + "paymentResult.jsp"; 
        } else if (action.equals("totalChiffreAffaire")) {
        	Myclub club = new Myclub();
        	BigDecimal chiffreAffaire = club.totalChiffreAffaire();
        	request.setAttribute("chiffreAffaire", chiffreAffaire);
        	maVue = VUES + "chiffreAffaireResult.jsp"; 
        	
        }else if (action.equals("addJoueur")) {
        	try {
        		Myclub myclub = new Myclub();

                // Retrieve and parse form data
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String typeAbonnement = request.getParameter("typeAbonnement");
                Integer clubId = Integer.parseInt(request.getParameter("clubId"));
                
                // Create a new joueur
                Joueur joueur = new Joueur();
                joueur.setNom(nom);
                joueur.setPrenom(prenom);

                // Associer le joueur au club
                Club club = myclub.findClubById(clubId);
                joueur.setClub(club);

                // Sauvegarder le joueur avant l'abonnement
                myclub.saveJoueur(joueur);

                // create abonnement
                Abonnement abonnement = new Abonnement();
                abonnement.setTypeAbonnement(typeAbonnement);
                abonnement.setJoueur(joueur); // Associer l'abonnement au joueur
                // Sauvegarder l'abonnement après avoir associé le joueur
                //myclub.saveAbonnement(abonnement);
                
                // create a new abonnement specifique
                if (typeAbonnement.equals("Forfait")) {
                	Forfait forfait = new Forfait();
                	// definir le type
                	forfait.setTypeAbonnement("Forfait");
                	//associer le joueur a l'abonnement
                	forfait.setJoueur(joueur);
                	// sauvegarder l'abonnement
                	myclub.saveAbonnement(forfait);
                }else if (typeAbonnement.equals("Ticket")) {
                	Ticket ticket = new Ticket();
                	// definir le type
                	ticket.setTypeAbonnement("Ticket");
                	//associer le joueur a l'abonnement
                	ticket.setJoueur(joueur);
                	// sauvegarder l'abonnement
                	myclub.saveAbonnement(ticket);
                }
                request.setAttribute("confirmationMessage", "Votre inscription a été enregistrée !");
                maVue = VUES + "inscriptionConfirmation.jsp";
				// TODO: handle exception
			}catch (Exception e) {
            	e.printStackTrace();
            	}
        	
        }
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(maVue);
        dispatcher.forward(request, response);
    }

}
