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
	return (Joueur) session.load(Joueur.class, id);
   }
   public  Club findClubById(Integer id) {
	return (Club) session.load(Club.class, id);
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
	   Query q = session.createQuery("from Installation i JOIN FETCH i.club");
	   return q.list();
   }
   
   public List<Object[]> getJoueurAbonnement(){
	   Query q = session.createQuery("SELECT j.nom, j.prenom, a.typeAbonnement FROM Joueur j LEFT JOIN j.abonnement a ");
	   return q.list();
	   
   }
   
   public List<Object[]> getReservations() {
	   Query q = session.createQuery("SELECT j.nom, j.prenom, r.dateHeure, r.duree, i.typeInstallation, i.id "
	   		+ "FROM Joueur j INNER JOIN j.reservations r JOIN r.installation i");
	   return q.list();
   }
   
   public List<Reservation> getAllReservations() {
	Query q = session.createQuery("SELECT r FROM Reservation r JOIN FETCH r.joueur JOIN FETCH r.installation");
	return q.list();
			
   }
   
   public boolean saveReservation(Reservation reservation) {
	    // Open a new session and start a transaction
	    Transaction transaction = null;
	    try  {
	        transaction = session.beginTransaction();
	        
	        //check if the reservation already exist for the given installation
	        Reservation existingReservation = (Reservation) session.createQuery("FROM Reservation WHERE installation = :installation AND dateHeure = :dateHeure", Reservation.class)
	        														.setParameter("installation", reservation.getInstallation())
	        														.setParameter("dateHeure", reservation.getDateHeure())
	        														.uniqueResult();
	        if (existingReservation != null) {
	        	// if a reservation already exist for the same installation and dateheure
	        	//throw new Exception("L'installation est déja réservée à ce momment-là. Veuillez choisir une autre heure.");
	        	return false;
	        }
	        // Save the reservation object
	        session.save(reservation);
	        transaction.commit();
	       
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return true;
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
				//BigDecimal prixAnnuel = BigDecimal.valueOf(forfait.getPrixAnnuel());
				BigDecimal prixAnnuel = forfait.getPrixAnnuel();
				totalpaye = totalpaye.add(prixAnnuel.divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
				//calculate the cost of reservation the last month
				for (Reservation reservation : joueur.getReservations()) {
					//check if the reservation in previous month
					if (isReservationInPreviousMonth(reservation)) {
						InstallationType type = InstallationType.valueOf(reservation.getInstallation().getTypeInstallation());
						BigDecimal prixParHeure = forfait.getPrixParHeure(type);
						totalpaye= totalpaye.add(prixParHeure.multiply(new BigDecimal(reservation.getDuree())));
					}
				}
				
			}else if (joueur.getAbonnement() instanceof Ticket){
				Ticket ticket = (Ticket) joueur.getAbonnement();
				for (Reservation reservation : joueur.getReservations()) {
	                // Vérifier si la réservation est du mois précédent
	                if (isReservationInPreviousMonth(reservation)) {
	                    InstallationType type = InstallationType.valueOf(reservation.getInstallation().getTypeInstallation());
	                    BigDecimal prixParHeure = ticket.getPrixParHeure(type);
	                    totalpaye = totalpaye.add(prixParHeure.multiply(new BigDecimal(reservation.getDuree())));
	                }
	            }
				
			}
		}
		
		return totalpaye; 
	}
   
   //une méthode qui indique le chiffre d’affaire du mois dernier   
   public BigDecimal totalChiffreAffaire() {
	    BigDecimal chiffreAffaire = BigDecimal.ZERO;

	    // Map pour stocker le coût mensuel par joueur
	    Map<Integer, BigDecimal> prixMensuelParJoueur = new HashMap<>();

	    List<Reservation> allReservations = getAllReservations();

	    for (Reservation reservation : allReservations) {
	        if (isReservationInPreviousMonth(reservation)) {
	            Joueur joueur = reservation.getJoueur();
	            BigDecimal prixParHeure;

	            // Vérifier le type d'abonnement
	            if (joueur.getAbonnement() instanceof Forfait) {
	            	Forfait forfait = (Forfait) joueur.getAbonnement();
	                InstallationType type = InstallationType.valueOf(reservation.getInstallation().getTypeInstallation());
	                prixParHeure = forfait.getPrixParHeure(type);

	                // Ajouter le coût mensuel pour les abonnements forfaitaires
	                if (!prixMensuelParJoueur.containsKey(joueur.getId())) {
	                    BigDecimal prixMensuelForfait = ((Forfait) joueur.getAbonnement()).getPrixAnnuel().divide(new BigDecimal(12), 2, RoundingMode.HALF_UP);
	                    prixMensuelParJoueur.put(joueur.getId(), prixMensuelForfait);
	                }
	            } else if (joueur.getAbonnement() instanceof Ticket) {
	            	Ticket ticket = (Ticket) joueur.getAbonnement();
	                InstallationType type = InstallationType.valueOf(reservation.getInstallation().getTypeInstallation());
	                prixParHeure = ticket.getPrixParHeure(type);
	            } else {
	                continue;
	            }

	            BigDecimal cost = prixParHeure.multiply(new BigDecimal(reservation.getDuree()));
	            chiffreAffaire = chiffreAffaire.add(cost);
	        }
	    }

	    // Ajouter le coût mensuel par joueur à la somme du chiffre d'affaires
	    chiffreAffaire = chiffreAffaire.add(prixMensuelParJoueur.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add));

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
	
	public void saveJoueur(Joueur joueur) {
		Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.save(joueur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 
    }
	
	public void saveAbonnement(Abonnement abonnement) {
		Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(abonnement);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
}
