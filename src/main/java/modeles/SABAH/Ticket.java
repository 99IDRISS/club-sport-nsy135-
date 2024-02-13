package modeles.SABAH;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "abonnement_id")
public class Ticket extends Abonnement{
	
	@Column(name = "prix_par_heure")
	private BigDecimal prixParHeure;
	public void setPrixParHeure(BigDecimal ph) {prixParHeure = ph ;}
//	public BigDecimal getPrixParHeure() {
//		return prixParHeure;
//	}
	
	// Ajouter une méthode pour récupérer le prix par heure en fonction du type d'installation
    public BigDecimal getPrixParHeure(InstallationType type) {
        BigDecimal prixParHeure;
        switch (type) {
            case Tennis:
                prixParHeure = new BigDecimal("22");
                break;
            case Badminton:
                prixParHeure = new BigDecimal("20");
                break;
            case Squash:
                prixParHeure = new BigDecimal("18");
                break;
            default:
                prixParHeure = BigDecimal.ZERO;
                break;
        }
        return prixParHeure;
    }
}
