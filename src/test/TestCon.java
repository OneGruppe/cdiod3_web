package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.IUserDAO.DALException;

public class TestCon {

	Statement stmt = null;
	Connection connection = null;

	String driverName = "com.mysql.jdbc.Driver";

	String serverName = "91.100.3.26"; // Use this server.
	String portNumber = "9865";
	String projectName = "CDIO3";
	String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + projectName;

	String username = "Eclipse-bruger";
	String password = "ySmTL37uDjYZmzyn";

	private ArrayList<String> roomsArray;

	/**
	 * Opret testforbindelse, smider exception hvis connection ikke er muligt.
	 * @throws DALException
	 */
	public void doConnection() throws DALException {
		try {
			// Load the JDBC driver
			Class.forName(driverName);

			// Create a connection to the database
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			throw new DALException("ClassNotFoundException: in doConnection(): " + e.getMessage());
		} catch (SQLException e) {
			// Could not connect to the database
			throw new DALException("SQLException in doConnection() : " + e.getMessage());
		}
	}

	/**
	 * Viser liste over roller i databasen
	 * @return ArrayList<String>
	 */
	public ArrayList<String> showListOfRoles() {
		roomsArray = new ArrayList<String>();
		String query = "SELECT * FROM roles";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String room = rs.getString("role");
				roomsArray.add("Rolle " + rs.getString("id") + ": " + room);
			}
			return roomsArray;

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Svarer om der er en user med det password i databasen
	 * @param usr username på brugeren
	 * @param pass password på brugeren
	 * @return boolean, true hvis bruger med password findes
	 */
	public boolean isUserAndPassCorrect(String usr, String pass) {
		boolean isMatch = false;
		String query = "SELECT * FROM users WHERE username='" + usr + "'";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getString("password").equals(pass)) {
					isMatch = true;
				}
			}
			return isMatch;

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage());
			return isMatch;
		}
	}
}