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

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM PRODUKTBATCH WHERE PB_ID = " + pbId);
	    try {
	    	if (!rs.first()) throw new DALException("Produktbatch " + pbId + " findes ikke");
	    	return new ProduktBatchDTO (rs.getInt("PB_ID"), rs.getInt("STATUS"), rs.getInt("RECEPT_ID"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM produktbatch");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchDTO(rs.getInt("PB_ID"), rs.getInt("RECEPT_ID"), rs.getInt("STATUS")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		MySQLAccess.doUpdate(
				"INSERT INTO PRODUKTBATCH(PB_ID, RECEPT_ID, STATUS) VALUES " +
						"(" + produktbatch.getPbId() + ", " + produktbatch.getReceptId() + ", " + produktbatch.getStatus() + ")"
				);
	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		// TODO Auto-generated method stub
		MySQLAccess.doUpdate(
				"UPDATE PRODUKTBATCH SET PB_ID = " + produktbatch.getPbId() +  ", STATUS = " + produktbatch.getStatus() + 
				", RECEPT_ID =  " + produktbatch.getReceptId() + " WHERE PB_ID = " +
				produktbatch.getPbId()
		);
	}

}
