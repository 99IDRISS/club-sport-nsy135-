package modeles.SABAH;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "abonnement_id")
public class Forfait extends Abonnement{
	
	@Column(name = "prix_annuel")
	private BigDecimal prixAnnuel;
	public void setPrixAnnuel(BigDecimal pa) {
		this.prixAnnuel = pa;
	}
	public BigDecimal getPrixAnnuel() {
		return prixAnnuel;
	}
	
	@Column(name = "prix_par_heure")
    private BigDecimal prixParHeure ;
	public void setPrixParHeure(BigDecimal ph) {
		this.prixParHeure = ph;
	}
	public BigDecimal getPrixParHeure() {
		return prixParHeure;
	}
}
