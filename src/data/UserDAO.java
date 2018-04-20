package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

	Statement stmt = null;
	Connection connection = null;

	final String driverName = "com.mysql.jdbc.Driver";
	String serverName = "91.100.3.26"; // Use this server. 
	String portNumber = "9865";
	String projectName = "CDIO3";
	String url ="jdbc:mysql://" + serverName + ":" + portNumber + "/" + projectName;

	String userName = "Eclipse-bruger"; 
	String password = "ySmTL37uDjYZmzyn";

	public boolean doConnection(){ 
		try {
			// Load the JDBC driver
			Class.forName(driverName);

			// Create a connection to the database
			connection = DriverManager.getConnection(url, userName, password);

		} catch (ClassNotFoundException e) {
			// Could not find the database driver 
			System.out.println("ClassNotFoundException : "+e.getMessage());
			return false;
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
			return false;
		}
		return true; 
	}

	@Override
	public UserDTO getUser(String username) throws DALException {
		System.out.println("*** DAO: getUser '" + username + "' ***");
		String query = "SELECT * FROM users WHERE username='" + username + "'";
		int userId = 0;
		String userName = null;
		String ini = null;
		String password = null;
		String cpr = null;

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			List<String> roleList = new ArrayList<String>();
			while(rs.next()) {
				userId = rs.getInt("id");
				userName = rs.getString("username");
				ini = rs.getString("ini");
				password = rs.getString("password");
				cpr = rs.getString("cpr");
				roleList.add("1");
			}
			UserDTO user = new UserDTO(userId, userName, password, ini, cpr, roleList);
			System.out.println("DAO rolelist:");
			System.out.println("Id: " + userId);
			System.out.println("Name: " + userName);
			System.out.println("Password: " + password);
			System.out.println("Initials: " + ini);
			System.out.println("CPR: " + cpr + "\n");
			return user;

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
			return null;
		}
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> UserList = new ArrayList<UserDTO>();
		List<String> roleList= new ArrayList<>();
		String query = "SELECT * FROM users;";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
			while (rs.next()) {
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String ini = rs.getString("ini");
				String cpr = rs.getString("cpr");
				int userId = rs.getInt("id");
				String rolesInt = rs.getString("role");
				roleList.add(rolesInt);

				UserDTO user = new UserDTO(userId, userName, password, ini, cpr, roleList);
				UserList.add(user);
			}
			return UserList;

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
			return null;
		}
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		System.out.println("\n----***** DAO: CreateUser: *****----");
		List<String> roleList = user.getRoles();
		System.out.println("RoleList:");
		for (String role : roleList) {System.out.println(role);	}
		
		String query = "INSERT INTO users (username, password, ini, cpr, role) "
				+ "VALUES(" + "'" + user.getUserName() + "'," 
				+ "'" + user.getPassword() + "'," 
				+ "'" + user.getIni() + "'," 
				+ "'" + user.getCpr() + "',"
				+ "'1' )";
		System.out.println("SQL query: " + query);

		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			System.out.println("----***** DAO: CreateUser: end *****----");

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
		}
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		String query = "UPDATE users SET username ='" + user.getUserName() + "', password ='" + user.getPassword() + "', ini ='" + user.getIni() + "',cpr ='" + user.getCpr() + "' WHERE id='" + user.getUserId() + "'";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
		}
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		String query = "DELETE FROM users where userId ='" + userId + "'";

		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
		}

	}

	public boolean isUserAndPassCorrect(String usr, String pass) {
		boolean isMatch = false;
		String query = "SELECT * FROM users WHERE username='" + usr + "'";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getString("password").equals(pass)){
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
