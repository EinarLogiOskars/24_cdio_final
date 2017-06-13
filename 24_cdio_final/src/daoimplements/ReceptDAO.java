package daoimplements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IReceptDAO;
import dbConnection.MySQLAccess;
import entity.ReceptDTO;

public class ReceptDAO  implements IReceptDAO {

	public ReceptDAO() {
		try { new MySQLAccess(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		// TODO Auto-generated method stub
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM receipt");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getInt("receiptID"), 
						rs.getString("name")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {	
		MySQLAccess.doUpdate(
				"INSERT INTO receipt(receiptID, name) values" + 
						"(" + recept.getReceptId() + ", '" + 
						recept.getReceptNavn() + "')" 
				);						
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		MySQLAccess.doUpdate(
				"UPDATE receipt SET receiptID = " + 
						recept.getReceptId() + ", name = '" + 
						recept.getReceptNavn() + "'  WHERE receiptID = " + recept.getReceptId());
	}

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM receipt WHERE receiptID = " + receptId);
		try {	    	
			if (!rs.first()) throw new DALException("RECEPT" + receptId  + " findes ikke");
			return new ReceptDTO (rs.getInt("receiptID"), rs.getString("name"));
		}
		catch (SQLException e) {throw new DALException(e); }

	}	
}
