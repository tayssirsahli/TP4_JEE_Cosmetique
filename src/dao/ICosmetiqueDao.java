package dao;
import java.util.List;
import metier.entities.Cosmetique;

public interface ICosmetiqueDao {
	public Cosmetique save(Cosmetique p);

	public List<Cosmetique> cosmetiquesParMC(String mc);

	public Cosmetique getCosmetique(Long id);

	public Cosmetique updateCosmetique(Cosmetique p);

	public void deleteCosmetique(Long id);
}