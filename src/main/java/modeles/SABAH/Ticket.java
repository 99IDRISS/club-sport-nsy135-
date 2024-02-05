package modeles.SABAH;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "abonnement_id")
public class Ticket extends Abonnement{
	
	@Column(name = "prix_par_heure")
	private BigDecimal prixParHeure;
	public void setPrixParHeure(BigDecimal ph) {prixParHeure = ph ;}
	public BigDecimal getPrixParHeure() {
		return prixParHeure;
	}
}
