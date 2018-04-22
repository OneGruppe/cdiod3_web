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

	@Override
	public boolean doConnection() throws DALException{ 
		try {
			// Load the JDBC driver
			Class.forName(driverName);

			// Create a connection to the database
			connection = DriverManager.getConnection(url, userName, password);

		} catch (ClassNotFoundException e) {
			throw new DALException("ClassNotFoundException: in doConnection(): " + e.getMessage());
		} catch (SQLException e) {
			throw new DALException("SQLException in doConnection() : " + e.getMessage());
		}
		return true; 
	}
	
	@Override
	public boolean closeConnection() throws DALException { 
		try {
			connection.close();
			Class.forName(driverName);

		} catch (ClassNotFoundException e) {
			throw new DALException("ClassNotFoundException in closeConnection(): " + e.getMessage());
		} catch (SQLException e) {
			throw new DALException("SQLException in closeConnection(): " + e.getMessage());
		}
		return true; 
	}

	@Override
	public int getUserId(String username) throws DALException {
		System.out.println("-----------------------DAO-GET-USER-ID---------------------");
		System.out.println("*** DAO: getUserId '" + username + "' ***");
		String query = "SELECT * FROM users WHERE username='" + username + "'";
		int userId = 0;
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				userId = rs.getInt("user_id");
			}
			System.out.println("Id: " + userId);
			System.out.println("");
			return userId;

		} catch (SQLException e) {
			throw new DALException("SQLException in getUserId(): " + e.getMessage());
		}
	}
	
	@Override
	public UserDTO getUser(String username) throws DALException {
		System.out.println("-------------------DAO - getUser(" + username + ")-------------------");
		String query = "SELECT * FROM totalView WHERE username='" + username + "'";
		int userId = 0;
		String userName = null;
		String ini = null;
		String password = null;
		String cpr = null;

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			List<String> roleList = new ArrayList<String>();
			if(rs.wasNull()) {throw new DALException("No users with this name exists");}
			
			while(rs.next()) {
				userId = rs.getInt("user_id");
				userName = rs.getString("username");
				password = rs.getString("password");
				cpr = rs.getString("cpr");
				ini = rs.getString("ini");
				roleList.add(rs.getString("role_id"));
			}
			UserDTO user = new UserDTO(userId, userName, password, ini, cpr, roleList);
			System.out.println(user.toString());
			System.out.println("");
			return user;

		} catch (SQLException e) {
			throw new DALException("SQLException in getUser(): " + e.getMessage());
		}
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		System.out.println("-------------------DAO - getUserList-------------------");
		List<UserDTO> userList = new ArrayList<UserDTO>();
		List<String> roleList= new ArrayList<>();
		String query = "SELECT * FROM totalView WHERE NOT username='admin'";
		int userId = 0;
		String userName = null;
		String password = null;
		String ini = null;
		String cpr = null;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
			while (rs.next()) {
				userId = rs.getInt("user_id");
				userName = rs.getString("username");
				password = rs.getString("password");
				ini = rs.getString("ini");
				cpr = rs.getString("cpr");
				roleList.add(rs.getString("role_id"));

				UserDTO user = new UserDTO(userId, userName, password, ini, cpr, roleList);
				userList.add(user);
			}
			/*System.out.println("Users gotten from server:");
			for(UserDTO user : userList) {System.out.println(user.toString());}
			System.out.println("");*/
			return userList;

		} catch (SQLException e) {
			throw new DALException("SQLException in getUserList(): " + e.getMessage());
		}
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		System.out.println("-------------------DAO - createUser-------------------");
		System.out.println("--- " + user.toString() + " ---");
		List<String> roleList = user.getRoles();

		String userQuery = "INSERT INTO users (username, password, ini, cpr) "
				+ "VALUES(" + "'" + user.getUserName() + "'," 
				+ "'" + user.getPassword() + "'," 
				+ "'" + user.getIni() + "'," 
				+ "'" + user.getCpr() + "')";
		System.out.println("SQL query: " + userQuery);

		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(userQuery);
			for (String role : roleList) {
				Statement stmt2 = connection.createStatement();
				stmt2.executeUpdate("INSERT INTO users_roles VALUES (" + getUserId(user.getUserName()) + ", " +  role + ")");
				System.out.println("INSERT INTO users_roles VALUES (" + getUserId(user.getUserName()) + ", " +  role + ")");
				System.out.println("");
			}

		} catch (SQLException e) {
			throw new DALException("SQLException in createUser(): " + e.getMessage());
		}
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		System.out.println("-------------------DAO - updateUser-------------------");
		System.out.println("--- " + user.toString() + " ---");
		String query = "UPDATE users SET username ='" + user.getUserName() + "', password ='" + user.getPassword() + "', ini ='" + user.getIni() + "',cpr ='" + user.getCpr() + "' WHERE id='" + user.getUserId() + "'";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			System.out.println("");
		} catch (SQLException e) {
			throw new DALException("SQLException in updateUser(): " + e.getMessage());
		}
	}

	@Override
	public void deleteUser(String userName) throws DALException {
		System.out.println("-------------------DAO - deleteUser(" + userName + ")-------------------");
		int userId = getUserId(userName);
		String usersQuery = "DELETE FROM users WHERE username ='" + userName + "'";
		String rolesusersQuery = "DELETE FROM roles_users WHERE users_id='" + userId + "'";
		try {
			Statement stmt = connection.createStatement();
			//stmt.executeUpdate(rolesusersQuery);
			stmt.executeUpdate(usersQuery);
			System.out.println("");

		} catch (SQLException e) {
			throw new DALException("SQLException in deleteUser(): " + e.getMessage());
		}

	}

}
