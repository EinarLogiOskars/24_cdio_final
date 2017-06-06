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

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM PRODUKTBATCHKOMPONENT WHERE PB_ID = " + pbId + " AND RB_ID = " + rbId);
	    try {
	    	if (!rs.first()) throw new DALException("Produktbatchkomponent " + pbId + " findes ikke");
	    	return new ProduktBatchKompDTO (rs.getInt("PB_ID"), rs.getInt("RB_ID"), rs.getInt("TARA"), rs.getDouble("NETTO"), rs.getInt("OPR_ID"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		//TODO getProduktBatchKompList with a parameter? Check it out.
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM PRODUKTBATCHKOMPONENT WHERE PB_ID = " + pbId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("PB_ID"), rs.getInt("RB_ID"), rs.getInt("TARA"), rs.getDouble("NETTO"), rs.getInt("OPR_ID")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM PRODUKTBATCHKOMPONENT");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("PB_ID"), rs.getInt("RB_ID"), rs.getDouble("TARA"), rs.getDouble("NETTO"), rs.getInt("OPR_ID")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		MySQLAccess.doUpdate(
				"INSERT INTO PRODUKTBATCHKOMPONENT(PB_ID, RB_ID, TARA, NETTO, OPR_ID) VALUES " +
						"(" + produktbatchkomponent.getPbId() + ", " + produktbatchkomponent.getRbId() + ", " + produktbatchkomponent.getTara() + 
						", " + produktbatchkomponent.getNetto() + ", " + produktbatchkomponent.getOprId() + ")"
						);
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		MySQLAccess.doUpdate(
				"UPDATE PRODUKTBATCHKOMPONENT SET PB_ID = " + produktbatchkomponent.getPbId() + ", RB_ID =  " + produktbatchkomponent.getRbId() + 
				", TARA = " + produktbatchkomponent.getTara() + ", NETTO = " + produktbatchkomponent.getNetto() + ", OPR_ID = " + produktbatchkomponent.getOprId() + "  WHERE PB_ID = " +
				produktbatchkomponent.getPbId() + " AND RB_ID = " + produktbatchkomponent.getRbId()
		);
	}

}
