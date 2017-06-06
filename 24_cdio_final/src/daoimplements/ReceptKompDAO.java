package daoimplements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IReceptKompDAO;
import dbConnection.MySQLAccess;
import entity.ReceptKompDTO;

public class ReceptKompDAO implements IReceptKompDAO {

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		// TODO Auto-generated method stub
		
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM RECEPTKOMPONENT WHERE RECEPT_ID = " + receptId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("RECEPT_ID"), 
						rs.getInt("RAAVARE_ID"), 
						rs.getInt("NOM_NETTO"), 
						rs.getInt("TOLERANCE")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;

	}
	
	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		// TODO Auto-generated method stub
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM RECEPTKOMPONENT WHERE RECEPT_ID = " + receptId + " AND RAAVARE_ID = " + raavareId);
		
		try {	    	
			if (!rs.first()) throw new DALException("RECEPTKOMPONENT " + receptId  + " findes ikke");
			return new ReceptKompDTO (rs.getInt("RECEPT_ID"), 
					rs.getInt("RAAVARE_ID"), 
					rs.getDouble("NOM_NETTO"), 
					rs.getDouble("TOLERANCE"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		// TODO Auto-generated method stub
		
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FORM RECEPTKOMPONENT" );
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("RECEPT_ID"), 
						rs.getInt("RAAVARE_ID"), 
						rs.getDouble("NOM_NETTO"), 
						rs.getDouble("TOLERANCE")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		// TODO Auto-generated method stub
		
		MySQLAccess.doUpdate(
				"INSERT INTO RECEPTKOMPONENT(RECEPT_ID, RAAVARE_ID, NOM_NETTO, TOLERANCE) values " +
				"(" + receptkomponent.getReceptId() + ", " + 
				receptkomponent.getRaavareId() + ", " + 
				receptkomponent.getNomNetto() + ", " +
				receptkomponent.getTolerance() + ")");						
	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		// TODO Auto-generated method stub
		MySQLAccess.doUpdate("UPDATE RECEPTKOMPONENT SET RECEPT_ID = " + 
		receptkomponent.getReceptId() + ", RAAVARE_ID = " + 
		receptkomponent.getRaavareId() + ", NOM_NETTO  = " + 
		receptkomponent.getNomNetto() + ", TOLERANCE = " + 
		receptkomponent.getTolerance() +  "  WHERE RECEPT_ID = " +
				receptkomponent.getReceptId() + " AND RAAVARE_ID = " + receptkomponent.getRaavareId() );
	}

}
