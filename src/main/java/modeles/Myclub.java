package modeles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modeles.SABAH.*;


public class Myclub{

   /**
    * Objet Session de Hibernate
    */
   private Session session;

   /**
    * Constructeur établissant une connexion avec Hibernate
    */
   public Myclub()  {
     Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
     
     // ICI ON AJOUTE LES CLASSES JPA
       configuration.addAnnotatedClass(Joueur.class);
       configuration.addAnnotatedClass(Reservation.class);
       configuration.addAnnotatedClass(Club.class);
       configuration.addAnnotatedClass(Installation.class);
       configuration.addAnnotatedClass(Abonnement.class);
       configuration.addAnnotatedClass(Forfait.class);
       configuration.addAnnotatedClass(Tennis.class);
       configuration.addAnnotatedClass(Squash.class);
       configuration.addAnnotatedClass(Ticket.class);
       configuration.addAnnotatedClass(Badminton.class);
     // FIN DE L'AJOUT DES CLASSES JPA
     try {
    	 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
         SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         session = sessionFactory.openSession();
     } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	  }
    
   }
   public  Joueur findJoueurById(Integer id) {
//	   Joueur joueur;
//	   joueur = session.get(Joueur.class, joueurId);
	return (Joueur) session.load(Joueur.class, id);
   }
   
   public Installation findInstallationById(Integer installationId) {
	    Installation installation = null;
	    installation = session.get(Installation.class, installationId);
	    return installation;
   }
   
   public List<Joueur> getListJoueurs(){
	   Query q = session.createQuery("from Joueur j JOIN FETCH j.abonnement"); // utilisation join fetch pour minimiser les requete SQl
	   return q.list();
   }
   
   public List<Installation> getInstallation() {
	   Query q = session.createQuery("from Installation");
	   return q.list();
   }
   
   public List<Object[]> getJoueurAbonnement(){
	   Query q = session.createQuery("SELECT j.nom, j.prenom, a.typeAbonnement FROM Joueur j LEFT JOIN j.abonnement a ");
	   return q.list();
	   
   }
   
   public List<Object[]> getReservations() {
	   Query q = session.createQuery("SELECT j.nom, j.prenom, r.dateHeure, r.duree, i.typeInstallation "
	   		+ "FROM Joueur j INNER JOIN j.reservations r JOIN r.installation i");
	   return q.list();
   }
   
   public List<Reservation> getAllReservations() {
	Query q = session.createQuery("SELECT r FROM Reservation r JOIN FETCH r.joueur JOIN FETCH r.installation");
	return q.list();
			
   }
   
   public void saveReservation(Reservation reservation) {
	    // Open a new session and start a transaction
	    Transaction transaction = null;
	    try  {
	        transaction = session.beginTransaction();
	        // Save the reservation object
	        session.save(reservation);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
   }

   public BigDecimal calculerTotalpayeMoisPrec(Integer joueurId) {
		
		BigDecimal totalpaye = BigDecimal.ZERO;
		Joueur joueur = findJoueurById(joueurId);
		
		//check subscription 
		if (joueur.getAbonnement() != null) {
			//if player is on a forfait subscription
			if (joueur.getAbonnement() instanceof Forfait) {
				Forfait forfait = (Forfait) joueur.getAbonnement();
				// add 1/12th of the annual fee
				BigDecimal prixAnnuel = BigDecimal.valueOf(forfait.getPrixAnnuel());
				totalpaye = totalpaye.add(prixAnnuel.divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			}
			//calculate the cost of reservation the last month
			for (Reservation reservation : joueur.getReservations()) {
				//check if the reservation in previous month
				if (isReservationInPreviousMonth(reservation)) {
					BigDecimal prixParHeure = (joueur.getAbonnement()instanceof Forfait)
							? ((Forfait) joueur.getAbonnement()).getPrixParHeure()
							: ((Ticket) joueur.getAbonnement()).getPrixParHeure();
				
					//add  the cost to total
					totalpaye= totalpaye.add(prixParHeure.multiply(new BigDecimal(reservation.getDuree())));
				}
			}
		}
		return totalpaye; 
	}
   //une méthode qui indique le chiffre d’affaire du mois dernier
   public BigDecimal totalChiffreAffaire() {
	   BigDecimal chiffreAffaire = BigDecimal.ZERO;
	 
	   List<Reservation> allReservations = getAllReservations() ;
	   
	   for (Reservation reservation :allReservations) {
		   if (isReservationInPreviousMonth(reservation)) {
			   Joueur joueur = reservation.getJoueur();
			   BigDecimal prixParHeure;
			   
			   //check type abonnement
			   if (joueur.getAbonnement() instanceof Forfait) {
				   prixParHeure= ((Forfait) joueur.getAbonnement()).getPrixParHeure();
			   }else if (joueur.getAbonnement() instanceof Ticket) {
				prixParHeure = ((Ticket) joueur.getAbonnement()).getPrixParHeure();
			   }else { 
				continue;
			   }
			   
			   BigDecimal cost = prixParHeure.multiply(new BigDecimal(reservation.getDuree()));
			   chiffreAffaire = chiffreAffaire.add(cost);
		   }
		   
	   }
	   
	   return chiffreAffaire;
   }
   
	
	private boolean isReservationInPreviousMonth(Reservation reservation) {
		Calendar reservationDate= Calendar.getInstance();
		reservationDate.setTime(reservation.getDateHeure());
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, -1);   // Move to the previousMonth -1
		
		//check if the reservation year and month match now
		
		return (reservationDate.get(Calendar.YEAR) == now.get(Calendar.YEAR)) && 
				(reservationDate.get(Calendar.MONTH) == now.get(Calendar.MONTH));
		
	}
	
	// methode pour parcourir toute les installations, et selon leur type extraire les informations.
	
	public List<Map<String, Object>> getDetailInstallations(){
		// charger les installations
		List<Installation> installations = getInstallation();
		List<Map<String, Object>> detailInstallations = new ArrayList<>();
		
		for (Installation installation : installations) {
			Map<String, Object> details = new HashMap<>();
			details.put("id", installation.getId());
	        details.put("typeInstallation", installation.getTypeInstallation());
	        details.put("dateMiseEnService", installation.getDateMiseEnService());
	        details.put("etatGeneral", installation.getEtatGeneral());
	        
	        if (installation instanceof Tennis) {
	        	Tennis tennis = (Tennis) installation;
	        	details.put("surface", tennis.getSurface());
	        }else if (installation instanceof Squash) {
	        	Squash squash = (Squash) installation;
	        	details.put("vitre", squash.getVitre());
	        	details.put("dateRemplacementParquet", squash.getDateRemplacementParquet());
	        }else if (installation instanceof Badminton){
	        	Badminton badminton = (Badminton) installation;
	        	details.put("dateAchatFilets", badminton.getDateAchatFilets());
	        	details.put("dateAchatPoteaux", badminton.getDateAchatPoteaux());
	        }
	        
	        detailInstallations.add(details);
		}
		return detailInstallations;
		
	}
  
}
