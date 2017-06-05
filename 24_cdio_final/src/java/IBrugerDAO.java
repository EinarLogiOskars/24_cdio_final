package java;

import java.util.List;

public interface IBrugerDAO {
	BrugerDTO getBruger(int oprId) throws DALException;
	List<BrugerDTO> getBrugerList() throws DALException;
	void createBruger(BrugerDTO opr) throws DALException;
	void updateBruger(BrugerDTO opr) throws DALException;


	public class DALException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DALException(String message) {
			super(message); 
		}    
	}
}