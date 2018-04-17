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
	String serverName;
	String portNumber;
	String url;
	String userName;
	String password;

	public UserDAO(String serverName, String portNumber, String url, String userName, String password) {
		this.serverName = serverName;
		this.portNumber = portNumber;
		this.url = url;
		this.userName = userName;
		this.password = password;		

		try {
			// Load the JDBC driver
			Class.forName(driverName);

			// Create a connection to the database
			connection = DriverManager.getConnection(url, userName, password);

		} catch (ClassNotFoundException e) {
			// Could not find the database driver 
			System.out.println("ClassNotFoundException : "+e.getMessage());
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
		}
	}

	@Override
	public UserDTO getUser(int userId) throws DALException {
		String query = "SELECT * FROM Users WHERE userID='" + userId + "'"+";";

		try {
			java.sql.Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
			String username = rs.getString("UserName");
			String ini = rs.getString("ini");
			String Password = rs.getString("password");
			String cpr = rs.getString("cpr");
			UserDTO User = new UserDTO(userId, username, Password, ini, cpr);

			return User;

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
			return null;
		}
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> UserList = new ArrayList<UserDTO>();
		String query = "SELECT * FROM Users;";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
			while (rs.next()) {
				String Username = rs.getString("Username");
				String Password = rs.getString("password");
				String ini = rs.getString("ini");
				String cpr = rs.getString("cpr");
				int UserId = rs.getInt("userId");
				UserDTO user = new UserDTO(UserId, Username, Password, ini, cpr);
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
		String query = "INSERT INTO Users ( username, password, ini, cpr) VALUES(" + "'" + user.getUserName() + "'" + "'" + user.getPassword()+ "'" + "'" + user.getIni() + "'" + "'" + user.getCpr() + "'" + ");";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
		}

	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		String query = "UPDATE users SET username =" +"'"+ user.getUserName() +"'"+ ", password =" +"'"+ user.getPassword() +"'"+ ", ini =" +"'"+ user.getIni() +"'"+ ",cpr =" +"'"+ user.getCpr() +"'"+ " WHERE id=" + user.getUserId() + ";";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
		}
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		String query = "DELETE FROM users where userId =" +userId+";";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query); 

		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage()); 
		}

	}

}
