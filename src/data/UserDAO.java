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
			
			System.out.println(userId);
			System.out.println("---------------------DAO-END--------------------------");
			return userId;

		} catch (SQLException e) {
			throw new DALException("SQLException in getUserId(): " + e.getMessage());
		}
	}
	
	@Override
	public UserDTO getUser(String username) throws DALException {
		System.out.println("-----------------------DAO-getUser-----------------------");
		System.out.println("*** DAO: getUser '" + username + "' ***");
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
			if(rs.wasNull()) {throw new DALException("Der er ingen bruger med dette navn");}
			
			while(rs.next()) {
				userId = rs.getInt("user_id");
				userName = rs.getString("username");
				password = rs.getString("password");
				cpr = rs.getString("cpr");
				ini = rs.getString("ini");
				roleList.add(rs.getString("role_id"));
			}
			System.out.println("Id: " + userId);
			System.out.println("Name: " + userName);
			System.out.println("Password: " + password);
			System.out.println("CPR: " + cpr);
			System.out.println("Initials: " + ini);
			for(String role : roleList) {System.out.println("Role:" + role);}
			UserDTO user = new UserDTO(userId, userName, password, ini, cpr, roleList);
			System.out.println("---------------------DAO-END--------------------------");
			return user;

		} catch (SQLException e) {
			throw new DALException("SQLException in getUser(): " + e.getMessage());
		}
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> UserList = new ArrayList<UserDTO>();
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
		System.out.println("Id: " + user.getUserId());
		System.out.println("Name: " + user.getUserName());
		System.out.println("Password: " + user.getPassword());
		System.out.println("CPR: " + user.getCpr());
		System.out.println("Initials: " + user.getIni());
		for (String role : roleList) {System.out.println("Role: " + role);}
		
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
				stmt.executeUpdate("INSERT INTO roles_users VALUES (" + role + ", " + getUserId(user.getUserName()) + ")");
			}
			
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
	public void deleteUser(String userName) throws DALException {
		System.out.println("-----------------------DAO-deleteUser------------------------");
		int userId = getUserId(userName);
		String usersQuery = "DELETE FROM users WHERE username ='" + userName + "'";
		String rolesusersQuery = "DELETE FROM roles_users WHERE users_id='" + userId + "'";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(rolesusersQuery);
			stmt.executeUpdate(usersQuery);
		System.out.println("-------------------END--DAO-deleteUser-----------------------");

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
		}

	}

}
