package data;

import java.util.List;

public interface IUserDAO {

	UserDTO getUser(int userId) throws DALException;
	
	/**
	 * Gets list of users from disk
	 * @return list of user objects
	 * @throws DALException
	 */
	List<UserDTO> getUserList() throws DALException;
	
	/**
	 * Create user and save to disk
	 * @param user object
	 * @throws DALException
	 */
	void createUser(UserDTO user) throws DALException;
	
	/**
	 * Replaces the user object with the same id
	 * @param user object
	 * @throws DALException
	 */
	void updateUser(UserDTO user) throws DALException;
	
	/**
	 * Deletes user object from specific 
	 * @param userId
	 * @throws DALException
	 */
	void deleteUser(int userId) throws DALException;

	//Exception-class
	public class DALException extends Exception {
		private static final long serialVersionUID = -648120467892409553L;
		public DALException() {
			super();
		}
		public DALException(String msg) {
			super(msg);
		}
	}
	
}
