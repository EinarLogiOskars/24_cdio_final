package daoimplements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IProduktBatchKompDAO;
import dbConnection.MySQLAccess;
import entity.ProduktBatchKompDTO;


public class ProduktBatchKompDAO implements IProduktBatchKompDAO {

	public ProduktBatchKompDAO() {
		try { new MySQLAccess(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM productbatchkomp WHERE pb_ID = " + pbId + " AND mbID = " + rbId);
	    try {
	    	if (!rs.first()) throw new DALException("productbatchkomp " + pbId + " findes ikke");
	    	return new ProduktBatchKompDTO (rs.getInt("pb_ID"), rs.getInt("mbID"), rs.getInt("tara"), rs.getDouble("netto"), rs.getInt("userID"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		//TODO getProduktBatchKompList with a parameter? Check it out.
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM productbatchkomp WHERE pb_ID = " + pbId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_ID"), rs.getInt("mbID"), rs.getInt("tara"), rs.getDouble("netto"), rs.getInt("userID")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM productbatchkomp");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_ID"), rs.getInt("mbID"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("userID")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		MySQLAccess.doUpdate(
				"INSERT INTO productbatchkomp(pb_ID, mbID) VALUES " +
						"(" + produktbatchkomponent.getPbId() + ", " + produktbatchkomponent.getRbId() + ")"
						);
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		MySQLAccess.doUpdate(
				"UPDATE productbatchkomp SET pb_ID = " + produktbatchkomponent.getPbId() + ", mbID =  " + produktbatchkomponent.getRbId() + 
				", tara = " + produktbatchkomponent.getTara() + ", netto = " + produktbatchkomponent.getNetto() + ", userID = " + produktbatchkomponent.getOprId() + "  WHERE pb_ID = " +
				produktbatchkomponent.getPbId() + " AND mbID = " + produktbatchkomponent.getRbId()
		);
	}

}
