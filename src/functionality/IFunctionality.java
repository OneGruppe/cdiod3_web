package functionality;

public interface IFunctionality {

	/**
	 * Styrer om man kommer ind på siden eller ej
	 * @param usr på brugeren
	 * @param pass på brugeren
	 * @return boolean der styrer om man logger ind eller ej.
	 */
	boolean login(String usr, String pass);

	/**
	 * Såfremt databasen er online, så gemmes der en liste til disk.
	 * @return boolean der styrer der kommer fejl eller ej til siden.
	 */
	boolean logout();

	/**
	 * Laver ny bruger i database
	 * @param name på brugeren
	 * @param password på brugeren
	 * @param ini på brugeren
	 * @param cpr på brugeren
	 * @param admin rolle på brugeren
	 * @param laborant rolle på brugeren
	 * @param farmaceut rolle på brugeren
	 * @param produktionsleder rolle på brugeren
	 * @return retur-string der vises på siden med alert();
	 */
	String createUser(String name, String password, String ini, String cpr, boolean admin, boolean laborant,
			boolean farmaceut, boolean produktionsleder);

	/**
	 * Ændrer bruger i database
	 * @param userName på brugeren
	 * @param newName på brugeren
	 * @param newPassword på brugeren
	 * @param newIni på brugeren
	 * @return retur-string der vises på siden med alert();
	 */
	String changeUser(String userName, String newName, String newPassword, String newIni);

	/**
	 * Sletter bruger i database
	 * @param userName
	 * @return retur-string der vises på siden med alert();
	 */
	String deleteUser(String userName);

	/**
	 * Viser én bruger fra databasen på side
	 * @param name
	 * @return JSON-array med user-objekter, konverteret til string, vises som tabel på siden
	 */
	String showUser(String name);

	/**
	 * Viser en liste over brugere fra databasen på side
	 * @return JSON-array med et array af user-objekter, konverteret til string, vises som tabel på siden
	 */
	String showUserList();

}