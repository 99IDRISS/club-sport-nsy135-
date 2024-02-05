package modeles.SABAH;

import java.util.Date;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "installation_id")
public class Squash extends Installation {
	
	 @Column
	 private boolean vitre;
	 public void setVitre(Boolean v) {vitre = v;}
	 public boolean getVitre() {return vitre;}

	 @Temporal(TemporalType.DATE)
	 @Column(name = "date_remplacement_parquet")
	 private Date dateRemplacementParquet;
	 public Date getDateRemplacementParquet() {
	        return dateRemplacementParquet;}
	 public void setDateRemplacementParquet(Date dateRemplacementParquet) {
	        this.dateRemplacementParquet = dateRemplacementParquet;}

}
