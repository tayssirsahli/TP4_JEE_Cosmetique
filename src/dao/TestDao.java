package dao;

import java.util.List;
import metier.entities.Cosmetique;

public class TestDao {
	public static void main(String[] args) {
		CosmetiqueDaoImpl pdao = new CosmetiqueDaoImpl();
		Cosmetique cosm = pdao.save(new Cosmetique("fond de teint", 80));
		System.out.println(cosm);
		List<Cosmetique> cosms = pdao.cosmetiquesParMC("parfum");
		for (Cosmetique p : cosms)
			System.out.println(p);
	}
}