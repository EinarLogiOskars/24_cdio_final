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

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		// TODO Auto-generated method stub
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM RECEPT");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getInt("RECEPT_ID"), 
						rs.getString("RECEPT_NAVN")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		// TODO Auto-generated method stub		
		MySQLAccess.doUpdate(
				"INSERT INTO RECEPT(RECEPT_ID, RECEPT_NAVN) values" + 
						"(" + recept.getReceptId() + ", '" + 
						recept.getReceptNavn() + "')" 
				);						
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		// TODO Auto-generated method stub

		MySQLAccess.doUpdate(
				"UPDATE RECEPT SET RECEPT_ID = " + 
						recept.getReceptId() + ", RECEPT_NAVN = '" + 
						recept.getReceptNavn() + "'  WHERE RECEPT_ID = " + recept.getReceptId());
	}

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		// TODO Auto-generated method stub

		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM RECEPT WHERE RECEPT_ID = " + receptId);
		try {	    	
			if (!rs.first()) throw new DALException("RECEPT" + receptId  + " findes ikke");
			return new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn"));
		}
		catch (SQLException e) {throw new DALException(e); }

	}	
}
