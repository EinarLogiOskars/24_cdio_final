package daointerfaces;

import java.util.List;

import daointerfaces.IBrugerDAO.DALException;
import entity.ReceptDTO;

public interface IReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO recept) throws DALException;
	void updateRecept(ReceptDTO recept) throws DALException;
}
 
