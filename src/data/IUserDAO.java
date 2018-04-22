package data;

import java.util.List;

public interface IUserDAO {

	/**
	 * Get user object from database
	 * @param userName - string, which is searched for.
	 * @return UserDTO object
	 * @throws DALException
	 */
	UserDTO getUser(String userName) throws DALException;
	
	/**
	 * Gets userID from database
	 * @param username - string, which is searched for.
	 * @return integer of the user-id.
	 * @throws DALException
	 */
	int getUserId(String username) throws DALException;
	
	/**
	 * Connects to database
	 * @return true if connection was created
	 * @throws DALException
	 */
	boolean doConnection() throws DALException;

	/**
	 * Closes the connection to database
	 * @return true if connection was closed
	 * @throws DALException
	 */
	boolean closeConnection() throws DALException;
	
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
	void deleteUser(String userName) throws DALException;

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
