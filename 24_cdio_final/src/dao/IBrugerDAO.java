package dao;

import java.util.ArrayList;
import java.util.List;
import entity.BrugerDTO;

public interface IBrugerDAO {
	BrugerDTO getBruger(int userId) throws DALException;
	List<BrugerDTO> getBrugerList() throws DALException;
	void createBruger(BrugerDTO user) throws DALException;
	void updateBruger(BrugerDTO user) throws DALException;
	void deleteBruger(int userId) throws DALException;
	ArrayList<Integer> getUserIds() throws DALException;


	public class DALException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DALException(String msg, Throwable e) {
			super(msg,e);
		}

		public DALException(String msg) {
			super(msg);
		}
		
		public DALException(Throwable e) {
			super(e);
		}
	}
}