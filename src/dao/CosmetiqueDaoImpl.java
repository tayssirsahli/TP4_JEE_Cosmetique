package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.SingletonConnection;
import metier.entities.Cosmetique;

public class CosmetiqueDaoImpl implements ICosmetiqueDao {
	@Override
	public Cosmetique save(Cosmetique p) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO COSMETIQUE(NOM_COSMETIQUE,PRIX) VALUES(?,?)");
			ps.setString(1, p.getNomCosmetique());
			ps.setDouble(2, p.getPrix());
			ps.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_COSMETIQUE) as MAX_ID FROM COSMETIQUE");
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				p.setIdCosmetique(rs.getLong("MAX_ID"));
			}
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;

	}

	@Override
	public List<Cosmetique> cosmetiquesParMC(String mc) {
		List<Cosmetique> cosms = new ArrayList<Cosmetique>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from COSMETIQUE where NOM_COSMETIQUE LIKE ?");
			ps.setString(1, "%" + mc + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cosmetique cos = new Cosmetique();
				cos.setIdCosmetique(rs.getLong("ID_COSMETIQUE"));
				cos.setNomCosmetique(rs.getString("NOM_COSMETIQUE"));
				cos.setPrix(rs.getDouble("PRIX"));
				cosms.add(cos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cosms;
	}

	@Override
	public  Cosmetique getCosmetique(Long id) {

		Connection conn = SingletonConnection.getConnection();
		Cosmetique p = new Cosmetique();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from COSMETIQUE where ID_COSMETIQUE = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p.setIdCosmetique(rs.getLong("ID_COSMETIQUE"));
				p.setNomCosmetique(rs.getString("NOM_COSMETIQUE"));
				p.setPrix(rs.getDouble("PRIX"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Cosmetique updateCosmetique(Cosmetique p) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE COSMETIQUE SET NOM_COSMETIQUE=?,PRIX=? WHERE ID_COSMETIQUE=?");
			ps.setString(1, p.getNomCosmetique());
			ps.setDouble(2, p.getPrix());
			ps.setLong(3, p.getIdCosmetique());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public void deleteCosmetique(Long id) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM COSMETIQUE WHERE ID_COSMETIQUE = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}