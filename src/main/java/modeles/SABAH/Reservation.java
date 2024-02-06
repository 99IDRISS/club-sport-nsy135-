package modeles.SABAH;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Integer id;
	public void setId(Integer i ) {this.id = i;}
	public Integer getId() {return id;}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "joueur_id")
	private Joueur joueur;
	public void setJoueur(Joueur j) {joueur = j ;}
	public Joueur getJoueur() {return joueur;}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "installation_id")
	private Installation installation;
	public void setInstallation(Installation i) {installation = i ;}
	public Installation getInstallation() {return installation;}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_heure")
    private Date dateHeure;
	public void setDateHeure(Date dh) {dateHeure = dh;}
	public Date getDateHeure() { return dateHeure;}
	
	@Column
	private Integer	duree;
	public void setDuree(Integer duree) {this.duree = duree;}
	public Integer getDuree() { return duree;}
	
	
}
