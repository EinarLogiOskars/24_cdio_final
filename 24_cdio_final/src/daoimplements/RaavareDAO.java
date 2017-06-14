package daoimplements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IRaavareDAO;
import dbConnection.MySQLAccess;
import entity.RaavareDTO;

public class RaavareDAO implements IRaavareDAO {

	public RaavareDAO() {
		try { new MySQLAccess(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM materials WHERE materialID = " + raavareId);
	    try {
	    	if (!rs.first()) throw new DALException("Raavare " + raavareId + " findes ikke");
	    	return new RaavareDTO(rs.getInt("materialID"), rs.getString("name"), rs.getString("supplier"));
	    } catch (SQLException e) { throw new DALException(e); }
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM materials");
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareDTO(rs.getInt("materialID"), rs.getString("name"), rs.getString("supplier")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException {
		MySQLAccess.doUpdate("INSERT INTO materials(name, supplier) VALUES('" + raavare.getRaavareNavn() + "', '" + raavare.getLeverandoer() + "')"
						);
	}

	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException {
		MySQLAccess.doUpdate(
				"UPDATE materials SET materialID = " + raavare.getRaavareId() + ", name =  '" + raavare.getRaavareNavn() + 
				"', supplier = '" + raavare.getLeverandoer() + "'  WHERE materialID = " + raavare.getRaavareId()
		);
	}

}
