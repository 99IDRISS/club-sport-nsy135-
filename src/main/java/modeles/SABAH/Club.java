package modeles.SABAH;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Club")
public class Club {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "club_id")
	private Integer id;
	//private void setId(Integer i) {id = i;}
	public Integer getId() {return id;}
	
	@Column(name = "nom")
	private String nom;
	public void setName(String n) {nom = n;}
	public String getName() {return nom;}
	
	@OneToMany(mappedBy = "club")
	private Set<Installation> installations = new HashSet<Installation>();
	public void setInstallations(Set<Installation> ins) {
		this.installations = ins;
	}
	public Set<Installation> getInstallations() {
		return this.installations;
	}

}
