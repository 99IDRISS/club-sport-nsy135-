package modeles.SABAH;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Abonnement {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "abonnement_id")
	 private Integer id;
	 public void setId(Integer i ) {this.id = i;}
	 public Integer getId() {return id;}
	 
	 @Column(name = "type_abonnement")
	 private String typeAbonnement;
	 public void setTypeAbonnement(String ab) {
		this.typeAbonnement = ab;
	 }
	 public String getTypeAbonnement() {
		return typeAbonnement;
	}
	 

	 @OneToOne
	 @JoinColumn(name = "joueur_id")
	 private Joueur joueur;
	 public void setJoueur(Joueur jo) {
		this.joueur = jo ;
	}
	 public Joueur getJoueur() {
		return joueur;
	}

}
