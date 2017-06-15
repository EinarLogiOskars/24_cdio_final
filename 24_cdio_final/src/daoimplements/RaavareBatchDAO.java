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
	public RaavareBatchDTO getRaavareBatch(int mbID) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM materialbatch WHERE mbID = " + mbID);
	    try {
	    	if (!rs.first()) throw new DALException("materialbatch " + mbID + " findes ikke");
	    	return new RaavareBatchDTO(rs.getInt("mbID"), rs.getInt("materialID"), rs.getDouble("amount"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM materialbatch");
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt("mbID"), rs.getInt("materialID"), rs.getDouble("amount")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		//TODO Same as MySQLProduktBatchKomp... return all RaavareBatches containing the specific raavareId???
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM materialbatch WHERE materialID = " + raavareId);
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt("mbID"), rs.getInt("materialID"), rs.getDouble("amount")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		MySQLAccess.doUpdate(
				"INSERT INTO materialbatch(mbID, materialID, amount) VALUES " +
						"(" + raavarebatch.getRbId() + ", " + raavarebatch.getRaavareId() + ", " + raavarebatch.getMaengde() + ")"
						);
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		MySQLAccess.doUpdate(
				"UPDATE materialbatch SET mbID = " + raavarebatch.getRbId() + ", materialID =  " + raavarebatch.getRaavareId() + 
				", amount = " + raavarebatch.getMaengde() + "  WHERE mbID = " + raavarebatch.getRbId()
		);
	}



}
