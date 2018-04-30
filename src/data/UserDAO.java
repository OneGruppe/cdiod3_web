package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

	private Connection connection = null;

	private final String driverName = "com.mysql.jdbc.Driver";
	
	/* Offline MySQL server med 'database_backup.sql' */
	//private String serverName = localhost
	// String portNumer = "3306"
	//private String userName = "root";
	//private String password = "password";
	
	/* Online MySQL server med remote connection */
	private String serverName = "91.100.3.26"; // Use this server.
	private String portNumber = "9865";
	private String userName = "Eclipse-bruger";
	private String password = "ySmTL37uDjYZmzyn";

	/* Databasenavn */
	private String projectName = "CDIO3";
	private String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + projectName;

	/**
	 * Opret testforbindelse, smider exception hvis connection ikke er muligt.
	 * @throws DALException
	 */
	public void doConnection() throws DALException {
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
	}

	/**
	 * getUser_idFromDatabase giver et userId, til brug af SQL sætnings-sendinger, bruges kun online.
	 * @param username brugernavn
	 * @return user_id på brugeren
	 * @throws DALException såfremt en SQL fejl sker.
	 */
	public int getUser_idFromDatabase(String username){
		String query = "SELECT * FROM users WHERE username='" + username + "'";
		int userId = 0;

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				userId = rs.getInt("user_id");
			}
			return userId;

		} catch (SQLException e) {
			System.out.println("SQLException in getUser_id(): " + e.getMessage());
			return userId;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see data.IUserDAO#getUser(java.lang.String)
	 */
	@Override
	public UserDTO getUser(String username) throws DALException {
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
			if (rs.wasNull()) {
				throw new DALException("No users with this name exists");
			}

			while (rs.next()) {
				userId = rs.getInt("user_id");
				userName = rs.getString("username");
				password = rs.getString("password");
				cpr = rs.getString("cpr");
				ini = rs.getString("ini");
				roleList.add(rs.getString("role_id"));
			}
			UserDTO user = new UserDTO(userId, userName, password, ini, cpr, roleList);
			return user;

		} catch (SQLException e) {
			throw new DALException("SQLException in getUser(): " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see data.IUserDAO#getUserList()
	 */
	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		String userQuery = "SELECT * FROM users WHERE NOT username='admin'";
		String roleQuery = "SELECT * from users_roles WHERE NOT users_id='1'";
		int user_id = 0;
		String userName = null;
		String password = null;
		String ini = null;
		String cpr = null;
		try {
			Statement stmt = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			ResultSet rsUsers = stmt.executeQuery(userQuery);
			ResultSet rsRoles = stmt2.executeQuery(roleQuery);
			while (rsUsers.next()) {
				user_id = rsUsers.getInt("user_id");
				userName = rsUsers.getString("username");
				password = rsUsers.getString("password");
				ini = rsUsers.getString("ini");
				cpr = rsUsers.getString("cpr");
				UserDTO user = new UserDTO(user_id, userName, password, ini, cpr, null);
				userList.add(user);
			}

			while (rsRoles.next()) {
				for (UserDTO usr : userList) {
					if (usr.getUser_id() == rsRoles.getInt("users_id")) {
						if (usr.getRoles() == null) {
							List<String> exRoleList = new ArrayList<String>();
							String lel = rsRoles.getString("roles_id");
							exRoleList.add(lel);
							usr.setRoles(exRoleList);
						} else {
							List<String> exRoleList = usr.getRoles();
							exRoleList.add(rsRoles.getString("roles_id"));
							usr.setRoles(exRoleList);
						}
					}
				}
			}
			return userList;

		} catch (SQLException e) {
			throw new DALException("SQLException in getUserList(): " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see data.IUserDAO#createUser(data.UserDTO)
	 */
	@Override
	public void createUser(UserDTO user) throws DALException {
		List<String> roleList = user.getRoles();

		String userQuery = "INSERT INTO users (username, password, ini, cpr) " + "VALUES(" + "'" + user.getUserName()
				+ "'," + "'" + user.getPassword() + "'," + "'" + user.getIni() + "'," + "'" + user.getCpr() + "')";

		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(userQuery);
			for (String role : roleList) {
				Statement stmt2 = connection.createStatement();
				stmt2.executeUpdate(
						"INSERT INTO users_roles VALUES (" + getUser_idFromDatabase(user.getUserName()) + ", " + role + ")");
			}

		} catch (SQLException e) {
			throw new DALException("SQLException in createUser(): " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see data.IUserDAO#updateUser(data.UserDTO)
	 */
	@Override
	public void updateUser(UserDTO user) throws DALException {
		String query = "UPDATE users SET username ='" + user.getUserName() + "', password ='" + user.getPassword()
				+ "', ini ='" + user.getIni() + "',cpr ='" + user.getCpr() + "' WHERE user_id='" + user.getUser_id()
				+ "'";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new DALException("SQLException in updateUser(): " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see data.IUserDAO#deleteUser(java.lang.String)
	 */
	@Override
	public void deleteUser(String userName) throws DALException {
		getUser_idFromDatabase(userName);
		String usersQuery = "DELETE FROM users WHERE username ='" + userName + "'";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(usersQuery);
		} catch (SQLException e) {
			throw new DALException("SQLException in deleteUser(): " + e.getMessage());
		}
	}

}
