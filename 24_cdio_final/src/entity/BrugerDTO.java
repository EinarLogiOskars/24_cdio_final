package entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;



public class BrugerDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private int	userId;                     
	private String userName;                
	private String ini;
	private String password;
	private String cpr;
	private List<String> roles;

	public BrugerDTO() {
		
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		if (!(password == null))
			this.password = password;
	}
	public void initializePassword() {
		this.password = UUID.randomUUID().toString();
	}
	public String getCpr(){
		return cpr;
	}
	public void setCpr(String cpr){
		this.cpr = cpr;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIni() {
		return ini;
	}
	public void setIni(String ini) {
		this.ini = ini;
	}

	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void addRole(String role){
		this.roles.add(role);
	}
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public boolean removeRole(String role){
		return this.roles.remove(role);
	}

	public void roleRemover (String role){
		roles.remove(role);
	}
	

	//	@Override
	//	public String toString() {
	//		return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", password=" + password
	//				+ ", cpr=" + cpr + ", roles=" + roles + "]";
	//	}


	//	@Override
	//	public String toString() {
	//		if (this.roles.isEmpty())
	//			return "UserId: " + userId + " | userName: " + userName + " | ini: " + ini + " | password: " + password
	//					+ " | cpr: " + cpr + " | roles: [User has no roles]";
	//		else
	//			return "UserId: " + userId + " | userName: " + userName + " | ini: " + ini + " | password: " + password
	//					+ " | cpr: " + cpr + " | roles: " + roles;
	//	}

	@Override
	public String toString() {
		return new StringBuffer("userId : ").append(this.userId)
				.append(" userName: ").append(this.userName)
				.append(" ini: ").append(this.ini).append(" password: ")
				.append(this.password).append(" cpr:").append(this.cpr)
				.append(" roles:").append(this.roles).toString();
		
	}
}
	
	
	
	
	