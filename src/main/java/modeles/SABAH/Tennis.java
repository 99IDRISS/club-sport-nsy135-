package modeles.SABAH;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "installation_id")
public class Tennis extends Installation {
	
	@Column
	private String surface;
	public void setSurface(String s) {
		this.surface = s;
	}
	public String getSurface() {
		return surface;
	}

}
