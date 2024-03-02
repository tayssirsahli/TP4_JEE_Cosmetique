package web;

import java.util.ArrayList;
import java.util.List;
import metier.entities.Cosmetique;

public class CosmetiqueModele {
	private String motCle;
	List<Cosmetique> cosmetiques = new ArrayList<>();

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public List<Cosmetique> getCosmetiques() {
		return cosmetiques;
	}

	public void setCosmetiques(List<Cosmetique> cosmetiques) {
		this.cosmetiques = cosmetiques;
	}
}