package modeles.SABAH;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Joueur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "joueur_id", unique = true, nullable = false, insertable = false, updatable = false, columnDefinition = "INT AUTO_INCREMENT")
	private Integer id;
	public void setId(Integer i ) {this.id = i;}
	public Integer getId() {return id;}
	
	@Column
	private String nom;
	public void setNom(String n) {nom = n;}
	public String getNom() {return nom;}
	
	@Column
	private String prenom;
	public void setPrenom(String p) {prenom = p;}
	public String getPrenom() {return prenom;}
	
	@OneToMany(mappedBy = "joueur")
	private Set<Reservation> reservations = new HashSet<Reservation>();
	public void setReservations(Set<Reservation> reser) {
		this.reservations = reser;
	}
	public Set<Reservation> getReservations() {
		return this.reservations;
	}
	
	@OneToOne(mappedBy = "joueur")
	private Abonnement abonnement;
	public void setAbonnement(Abonnement a) {
		this.abonnement = a;
	}
	public Abonnement getAbonnement() {
		return abonnement;
	}
	
	@ManyToOne
	@JoinColumn(name= "club_id")
	private Club club ;
	public void setClub(Club c) {
		this.club = c;
	}
	public Club getClub() {
		return club;
	}
	
}
