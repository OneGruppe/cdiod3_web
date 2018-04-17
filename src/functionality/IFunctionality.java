package functionality;

import data.*;

public interface IFunctionality {
	

	/**
	 * Changes the user object to a new user object, created with same ID.
	 * @param id of the user
	 * @param newName of the user
	 * @param newPassword of the user
	 * @param newIni of the user
	 * @throws DALException - exception handling
	 */
	void changeUser(int id, String newName, String newPassword, String newIni) ;

	/**
	 * Creates a user object, and writes that to the disk via. UserDAO
	 * @param name of the user
	 * @param password of the user
	 * @param ini of the user
	 * @param cpr of the user
	 * @throws DALException - exception handling
	 */
	void createUser(String name, String password, String ini, String cpr) ;

	/**
	 * Deletes a user from disk array.
	 * @param id of the user
	 * @throws DALException - exception handling
	 */
	void deleteUser(int id);

	/**
	 * Get a user from the disk, returns object, to print its toString()
	 * @param id of the user
	 * @return the object (UserDTO)
	 * @throws DALException - exception handling
	 */
	UserDTO showUser(int id);

	/**
	 * Get a toString from the disk, returns object with 
	 * @param id of the user
	 * @return a string containing the objects toString() + password and CPR from user.
	 * @throws DALException - exception handling
	 */
	String showUserAdmin(int id);

	/**
	 * String containing users
	 * @return a string with all objects toString() combined.
	 * @throws DALException - exception handling
	 */
	String showUserList();

	/**
	 * String containing users (admin)
	 * @return a string containing all the objects toString() + password and CPR from user.
	 * @throws DALException - exception handling
	 */
	String showUserListAdmin() ;

}
