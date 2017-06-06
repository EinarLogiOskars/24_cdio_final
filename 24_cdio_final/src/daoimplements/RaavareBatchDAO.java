package daoimplements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IRaavareBatchDAO;
import dbConnection.MySQLAccess;
import entity.RaavareBatchDTO;

public class RaavareBatchDAO implements IRaavareBatchDAO {

	public RaavareBatchDAO() {
		try { new MySQLAccess(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM RAAVAREBATCH WHERE RB_ID = " + rbId);
	    try {
	    	if (!rs.first()) throw new DALException("Raavarebatch " + rbId + " findes ikke");
	    	return new RaavareBatchDTO(rs.getInt("RB_ID"), rs.getInt("RAAVARE_ID"), rs.getDouble("MAENGDE"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM RAAVAREBATCH");
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt("RB_ID"), rs.getInt("RAAVARE_ID"), rs.getDouble("MAENGDE")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		//TODO Same as MySQLProduktBatchKomp... return all RaavareBatches containing the specific raavareId???
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM RAAVAREBATCH WHERE RAAVARE_ID = " + raavareId);
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt("RB_ID"), rs.getInt("RAAVARE_ID"), rs.getDouble("MAENGDE")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		MySQLAccess.doUpdate(
				"INSERT INTO RAAVAREBATCH(RB_ID, RAAVARE_ID, MAENGDE) VALUES " +
						"(" + raavarebatch.getRbId() + ", " + raavarebatch.getRaavareId() + ", " + raavarebatch.getMaengde() + ")"
						);
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		MySQLAccess.doUpdate(
				"UPDATE RAAVAREBATCH SET RB_ID = " + raavarebatch.getRbId() + ", RAAVARE_ID =  " + raavarebatch.getRaavareId() + 
				", MAENGDE = " + raavarebatch.getMaengde() + "  WHERE RB_ID = " + raavarebatch.getRbId()
		);
	}



}
