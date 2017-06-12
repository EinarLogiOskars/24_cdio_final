package daoimplements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces.IBrugerDAO;
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
		List<String> rolesList = new ArrayList<String>();
		try {
			while(rs.next()){
				user.setUserId(rs.getInt("userID"));
				user.setUserName(rs.getString("name"));
				user.setIni(rs.getString("initials"));
				user.setCpr(rs.getString("cpr"));
				user.setPassword(rs.getString("password"));			
			}
			ResultSet rs2 = MySQLAccess.doQuery("SELECT a.name FROM roles a, users b, user_roles c "
					+ "WHERE a.roleID = c.roleID "
					+ "AND b.userID = c.userID "
					+ "AND b.userID = " + userId + " "
					+ "order by a.roleID");
			while(rs2.next()){
				rolesList.add(rs2.getString("name"));
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
				int userId = rs.getInt("userID");
				user.setUserId(userId);
				user.setUserName(rs.getString("name"));
				user.setIni(rs.getString("initials"));
				user.setCpr(rs.getString("cpr"));
				user.setPassword(rs.getString("password"));
				userList1.add(user);
			}
			for(BrugerDTO u : userList1){
				List<String> rolesList = getRoles(u.getUserId());
				u.setRoles(rolesList);
			}
		} catch (SQLException e) { e.printStackTrace(); }

		return userList1;
	}

	@Override
	public void createBruger(BrugerDTO user) {
		try {
			MySQLAccess.doUpdate("INSERT INTO users(userID, name, initials, password, cpr) VALUES "
					+ "(" + user.getUserId() + ", '" + user.getUserName() + "', '" + user.getIni().toUpperCase() + "', '" + new passwordGenerator().createPassword()
					+ "', '" + user.getCpr() + "')");
			List<String> roles = user.getRoles();
			for(String a : roles){
				MySQLAccess.doUpdate("INSERT INTO user_roles(userID, roleID) SELECT " + user.getUserId() + ", a.roleID"
						+ " FROM roles a WHERE a.name = '" + a + "';");
			}
		} catch (DALException e) { e.printStackTrace(); }
	}

	@Override
	public void updateBruger(BrugerDTO user) throws DALException {
		MySQLAccess.doUpdate("UPDATE users SET name = '" + user.getUserName() + "', initials = '" + user.getIni() + "', password = '"
				+ user.getPassword() + "', cpr = '" + user.getCpr() + "' WHERE userID = " + user.getUserId());
		MySQLAccess.doUpdate("DELETE FROM user_roles WHERE userID = " + user.getUserId());
		List<String> roles = user.getRoles();
		for(String a : roles){
			MySQLAccess.doUpdate("INSERT INTO user_roles(userID, roleID) SELECT " + user.getUserId() + ", a.roleID"
					+ " FROM roles a WHERE a.name = '" + a + "';");
		}
		
	}

	@Override
	public void deleteBruger(int userId) throws DALException {
		MySQLAccess.doUpdate("DELETE FROM users WHERE userID = " + userId);		
		MySQLAccess.doUpdate("DELETE FROM user_roles WHERE userID = " + userId);
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
		
		ResultSet rs = MySQLAccess.doQuery("SELECT a.name FROM roles a, users b, user_roles c "
				+ "WHERE a.roleID = c.roleID "
				+ "AND b.userID = c.userID "
				+ "AND b.name = '" + username + "' "
				+ "ORDER BY a.roleID");
		String roles = "";
		try {
			while(rs.next()){
				roles = roles + rs.getString("name") + ", ";
			}
		} catch (SQLException e) {e.printStackTrace(); }
		
		return roles;
	}
	
	public List<String> getRoles(int userId) throws DALException{
		ResultSet rs = MySQLAccess.doQuery("SELECT a.name FROM roles a, users b, user_roles c "
				+ "WHERE a.roleID = c.roleID "
				+ "AND b.userID = c.userID "
				+ "AND b.userID = " + userId + " "
				+ "order by a.roleID");
		List<String> rolesList = new ArrayList<String>();
		try {
			while(rs.next()){
				rolesList.add(rs.getString("name"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rolesList;
	}

}
