package modeles.SABAH;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Installation")
@Inheritance(strategy = InheritanceType.JOINED)
public class Installation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "installation_id")
	private Integer id;
	public void setId(Integer i ) {this.id = i;}
	public Integer getId() {return id;}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_mise_en_service")
	private Date dateMiseEnService;
	public Date getDateMiseEnService() {
        return dateMiseEnService;
    }
    public void setDateMiseEnService(Date dateMiseEnService) {
        this.dateMiseEnService = dateMiseEnService;
    }
    
	@Column(name = "etat_general")
    private int etatGeneral;
	public int getEtatGeneral() {
        return etatGeneral;
    }

    public void setEtatGeneral(int etatGeneral) {
        this.etatGeneral = etatGeneral;
    }
    
    @Column(name = "type_installation")
    private String typeInstallation;
    public String getTypeInstallation() {
        return typeInstallation;
    }
    public void setTypeInstallation(String typeInstallation) {
        this.typeInstallation = typeInstallation;
    }
    
	@ManyToOne 
	@JoinColumn(name = "club_id")
	private Club club;
	public void setClub(Club c) {club = c;}
	public Club getClub() {return club;}
}
