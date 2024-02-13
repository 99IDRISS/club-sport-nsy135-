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
	
//	@Column(name = "prix_par_heure")
//    private BigDecimal prixParHeure ;
//	public void setPrixParHeure(BigDecimal ph) {
//		this.prixParHeure = ph;
//	}
//	public BigDecimal getPrixParHeure() {
//		return prixParHeure;
//	}
//	
	public BigDecimal getPrixParHeure(InstallationType type) {
		BigDecimal prixParHeure;
		switch (type) {
			case Tennis:
				prixParHeure = new BigDecimal("11");
				break;
			case Squash:
				prixParHeure = new BigDecimal("9");
				break;
			case Badminton:
				prixParHeure = new BigDecimal("10");
				break;
		default:
			prixParHeure = BigDecimal.ZERO;
			break;
		}
		return prixParHeure;
	}
	
}
