package daoimplements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IProduktBatchDAO;
import dbConnection.MySQLAccess;
import entity.ProduktBatchDTO;

public class ProduktBatchDAO implements IProduktBatchDAO{

	public ProduktBatchDAO() {
		try { new MySQLAccess(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public ProduktBatchDTO getProduktBatch(int pbID) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM productbatch WHERE pbID = " + pbID);
	    try {
	    	if (!rs.first()) throw new DALException("productbatch " + pbID + " findes ikke");
	    	return new ProduktBatchDTO (rs.getInt("pbID"), rs.getInt("status"), rs.getInt("receiptID"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM productbatch");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchDTO(rs.getInt("pbID"), rs.getInt("receiptID"), rs.getInt("status")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		MySQLAccess.doUpdate(
				"INSERT INTO productbatch(pbID, receiptID, status) VALUES " +
						"(" + produktbatch.getPbId() + ", " + produktbatch.getReceptId() + ", " + produktbatch.getStatus() + ")"
				);
	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		// TODO Auto-generated method stub
		MySQLAccess.doUpdate(
				"UPDATE productbatch SET pbID = " + produktbatch.getPbId() +  ", status = " + produktbatch.getStatus() + 
				", receiptID =  " + produktbatch.getReceptId() + " WHERE pbID = " +
				produktbatch.getPbId()
		);
	}

}
