package data;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 3860480211274703570L;
	private int userId;                     
	private String userName;                
	private String password;
	private String ini;                 
	private String cpr;
	private List<String> roles;

	public UserDTO(int userId, String userName, String password, String ini, String cpr) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.ini = ini;
		this.cpr = cpr;
	}

	/**
	 * @return id of the user
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @return name of the user
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Change username of the userobject.
	 * @param userName of the user
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Change password of the userobject.
	 * @param password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return initials of the userobject
	 */
	public String getIni() {
		return ini;
	}

	/**
	 * Change initials of the userobject.
	 * @param ini of the user
	 */
	public void setIni(String ini) {
		this.ini = ini;
	}
	
	/**
	 * @return CPR of the user
	 */
	public String getCpr() {
		return cpr;
	}
	
	/**
	 * Change CPR of the userobject.
	 * @param cpr of the user
	 */
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	/**
	 * Overrides the user objects toString() 
	 */
	@Override
	public String toString() {
		return "ID: " + userId + ", name: " + userName + ", initials: " + ini;
	}
	
}
