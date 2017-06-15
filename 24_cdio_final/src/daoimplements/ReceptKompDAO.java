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

	public ReceptKompDAO() {
		try { new MySQLAccess(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		// TODO Auto-generated method stub
		
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM receiptkomp WHERE receiptID = " + receptId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("receiptID"), 
						rs.getInt("materialID"), 
						rs.getInt("nonNetto"), 
						rs.getInt("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;

	}
	
	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		// TODO Auto-generated method stub
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM receiptkomp WHERE receiptID = " + receptId + " AND materialID = " + raavareId);
		
		try {	    	
			if (!rs.first()) throw new DALException("ReceiptKomponent " + receptId  + " findes ikke");
			return new ReceptKompDTO (rs.getInt("receiptID"), 
					rs.getInt("materialID"), 
					rs.getDouble("nonNetto"), 
					rs.getDouble("tolerance"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		// TODO Auto-generated method stub
		
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FORM receiptkomp" );
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("receiptID"), 
						rs.getInt("materialID"), 
						rs.getDouble("nonNetto"), 
						rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		// TODO Auto-generated method stub
		
		MySQLAccess.doUpdate(
				"INSERT INTO receiptkomp(receiptID, materialID, nonNetto, tolerance) values " +
				"(" + receptkomponent.getReceptId() + ", " + 
				receptkomponent.getRaavareId() + ", " + 
				receptkomponent.getNomNetto() + ", " +
				receptkomponent.getTolerance() + ")");						
	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		// TODO Auto-generated method stub
		MySQLAccess.doUpdate("UPDATE receiptkomp SET receiptID = " + 
		receptkomponent.getReceptId() + ", materialID = " + 
		receptkomponent.getRaavareId() + ", nonNetto  = " + 
		receptkomponent.getNomNetto() + ", tolerance = " + 
		receptkomponent.getTolerance() +  "  WHERE receiptID = " +
				receptkomponent.getReceptId() + " AND materialID = " + receptkomponent.getRaavareId() );
	}

}
