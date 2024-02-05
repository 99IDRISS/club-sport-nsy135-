package modeles.SABAH;

import java.util.Date;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "installation_id")
public class Badminton extends Installation {

	 @Temporal(TemporalType.DATE)
	 @Column(name = "date_achat_filets")
	 private Date dateAchatFilets;
	 public Date getDateAchatFilets() {
	        return dateAchatFilets;}
	 public void setDateAchatFilets(Date dateAchatFilets) {
	        this.dateAchatFilets = dateAchatFilets;}
	 
	 @Temporal(TemporalType.DATE)
	 @Column(name = "date_achat_poteaux")
	 private Date dateAchatPoteaux;
	 public Date getDateAchatPoteaux() {
	        return dateAchatFilets;}
	 public void setDateAchatPoteaux(Date dateAchatPoteaux) {
	        this.dateAchatPoteaux = dateAchatPoteaux;}

}
