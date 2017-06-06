package daoimplements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces.IBrugerDAO;
import daointerfaces.IBrugerDAO.DALException;
import dbConnection.MySQLAccess;
import entity.BrugerDTO;
import entity.passwordGenerator;



public class BrugerDAO implements IBrugerDAO{

	
	
	static ArrayList<BrugerDTO> userList = new ArrayList<BrugerDTO>();

	public BrugerDAO() {
		try { new MySQLAccess(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public boolean validateBruger(BrugerDTO user) throws DALException{
		
		ResultSet rs = MySQLAccess.doQuery("Select name FROM users");
		boolean userExists = false;
		boolean validated = false;
		try {
			while(rs.next()){
				if(rs.getString("name").equals(user.getUserName())){
					userExists = true;
					break;
				}
			}
			if(userExists){
				ResultSet rs2 = MySQLAccess.doQuery("Select password FROM users WHERE name = '" + user.getUserName() + "'");
				while(rs2.next()){
					if(user.getPassword().equals(rs2.getString("password"))){
						validated = true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(validated == true)
			return true;
		else
			return false;
		
		
	}

	@Override
	public BrugerDTO getBruger(int userId) throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM users WHERE userID = " + userId); 
		BrugerDTO user = new BrugerDTO();
		try {
			while(rs.next()){
				user.setUserId(rs.getInt("userID"));
				user.setUserName(rs.getString("name"));
				user.setIni(rs.getString("initials"));
				user.setCpr(rs.getString("cpr"));
				user.setPassword(rs.getString("password"));
				String roles = rs.getString("roles");
				List<String> rolesList = new ArrayList<String>();
				if (roles.contains("Admin"))
					rolesList.add("Admin");
				if (roles.contains("Operator"))
					rolesList.add("Operator");
				if (roles.contains("Foreman"))
					rolesList.add("Foreman");
				if (roles.contains("Pharmacist"))
					rolesList.add("Pharmacist");
				user.setRoles(rolesList);				
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return user; 
	}

	@Override
	public List<BrugerDTO> getBrugerList() throws DALException {
		ResultSet rs = MySQLAccess.doQuery("SELECT * FROM users");
		List<BrugerDTO> userList1 = new ArrayList<BrugerDTO>();

		try {
			while (rs.next()){
				BrugerDTO user = new BrugerDTO();
				user.setUserId(rs.getInt("userID"));
				user.setUserName(rs.getString("name"));
				user.setIni(rs.getString("initials"));
				user.setCpr(rs.getString("cpr"));
				user.setPassword(rs.getString("password"));
				String roles = rs.getString("roles");
				List<String> rolesList = new ArrayList<String>();
				if (roles.contains("Admin"))
					rolesList.add("Admin");
				if (roles.contains("Operator"))
					rolesList.add("Operator");
				if (roles.contains("Foreman"))
					rolesList.add("Foreman");
				if (roles.contains("Pharmacist"))
					rolesList.add("Pharmacist");
				user.setRoles(rolesList);
				userList1.add(user);
			}
		} catch (SQLException e) { e.printStackTrace(); }

		return userList1;
	}

	@Override
	public void createBruger(BrugerDTO user) {
		try {
			MySQLAccess.doUpdate("INSERT INTO users(userID, name, initials, password, cpr, roles) VALUES "
					+ "(" + user.getUserId() + ", '" + user.getUserName() + "', '" + user.getIni().toUpperCase() + "', '" + new passwordGenerator().createPassword()
					+ "', '" + user.getCpr() + "', '" + user.getRoles() + "')");
		} catch (DALException e) { e.printStackTrace(); }
	}

	@Override
	public void updateBruger(BrugerDTO user) throws DALException {
		MySQLAccess.doUpdate("UPDATE users SET name = '" + user.getUserName() + "', initials = '" + user.getIni() + "', password = '"
				+ user.getPassword() + "', cpr = '" + user.getCpr() + "', roles = '" + user.getRoles() + "' WHERE userID = " + user.getUserId());
	}

	@Override
	public void deleteBruger(int userId) throws DALException {
		MySQLAccess.doUpdate("DELETE FROM USERS WHERE userID = " + userId);		
	}

	@Override
	public ArrayList<Integer> getUserIds() throws DALException {
		ArrayList<Integer> userIds = new ArrayList<Integer>();

		ResultSet rs = MySQLAccess.doQuery("SELECT userID FROM users");

		try {
			while(rs.next()){
				userIds.add(rs.getInt("userID"));
			}
		} catch (SQLException e) { e.printStackTrace(); }

		return userIds;
	}

	@Override
	public String getRights(String username) throws DALException{
		
		ResultSet rs = MySQLAccess.doQuery("SELECT roles FROM users WHERE name = '" + username + "'");
		String roles = "";
		try {
			while(rs.next()){
				roles = rs.getString("roles");
			}
		} catch (SQLException e) {e.printStackTrace(); }
		
		return roles;
	}

}
