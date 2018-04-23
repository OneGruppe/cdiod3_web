package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OfflineUserDAO implements IUserDAO, Serializable {

	private static final long serialVersionUID = 1901505897256342849L;

	private List<UserDTO> users;

	public OfflineUserDAO(){
		try {
			users = getUserList();
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveToFile(List<UserDTO> userlist) throws DALException {
		try {
			users = getUserList();
		} catch (DALException e) {
			users = new ArrayList<UserDTO>();
		} finally {
			users.clear();
			users = userlist;
			for(int i = 0 ; i < userlist.size() ; i++) {
				if(userlist.get(i).getUserName().equals("admin")) {
					userlist.remove(i);
				}	
			}
			try {
				FileOutputStream saveFile = new FileOutputStream("users.sav");
				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				save.writeObject(users);
				save.close(); 
			} catch (IOException exc) {
				throw new DALException("There was an error trying to create a user");
			}
		}
	}

	@Override
	public UserDTO getUser(String userName) throws DALException {
		// Create the data objects for us to restore.
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			// Open file to read from, named users.sav.
			FileInputStream saveFile = new FileInputStream("users.sav");
			// Create an ObjectInputStream to get objects from save file.
			ObjectInputStream input = new ObjectInputStream(saveFile);
			// Now we do the restore.
			userList = (ArrayList<UserDTO>) input.readObject();
			// Close the file.
			input.close();
		} catch (ClassNotFoundException exc) {
			throw new DALException("There was an error trying to fetch data");
		} catch (IOException exc) {
			throw new DALException("There is no user, with this ID");
		}
		UserDTO user = null;
		for (UserDTO usr : userList) {
			if(usr.getUserName().equals(userName)) {
				user = usr;
				break;
			}
		}
		return user;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		// Create the data objects for us to restore.
		ArrayList<UserDTO> userList = (ArrayList<UserDTO>) this.users;
		try {
			FileInputStream saveFile = new FileInputStream("users.sav");
			ObjectInputStream input = new ObjectInputStream(saveFile);
			userList = (ArrayList<UserDTO>) input.readObject();
			input.close();
		} catch (ClassNotFoundException exc) {
			throw new DALException("There was an error trying to fetch data");
		} catch (IOException exc) {
			throw new DALException("There are no users to show");
		}
		return userList;
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		try {
			users = getUserList();
		} catch (DALException e) {
			users = new ArrayList<UserDTO>();
		} finally {
			users.add(user.getUser_id()-1, user);;
			try {
				FileOutputStream saveFile = new FileOutputStream("users.sav");
				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				save.writeObject(users);
				save.close(); 
			} catch (IOException exc) {
				throw new DALException("There was an error trying to create a user");
			}
		}
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		ArrayList<UserDTO> userList = (ArrayList<UserDTO>) this.users;
		try {
			FileInputStream saveFile = new FileInputStream("users.sav");
			ObjectInputStream input = new ObjectInputStream(saveFile);
			userList = (ArrayList<UserDTO>) input.readObject();
			input.close();
		} catch (ClassNotFoundException exc) {
			throw new DALException("There was an error trying to fetch data");
		} catch (IOException exc) {
			throw new DALException("There was an error trying to create a user");
		}
		for (UserDTO u : userList) {
			if ((u.getUser_id()) == (user.getUser_id())) {
				u.setUserName(user.getUserName());
				u.setIni(user.getIni());
				u.setPassword(user.getPassword());
				break;
			}
		}
		try {
			FileOutputStream saveFile = new FileOutputStream("users.sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(userList);
			save.close();

		} catch (IOException exc) {
			throw new DALException("There was an error trying to create a user");
		}
	}

	@Override
	public void deleteUser(String userName) throws DALException {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			FileInputStream saveFile = new FileInputStream("users.sav");
			ObjectInputStream input = new ObjectInputStream(saveFile);
			userList = (ArrayList<UserDTO>) input.readObject();
			input.close();
		} catch (ClassNotFoundException exc) {
			throw new DALException("There was an error trying to fetch data");
		} catch (IOException exc) {
			throw new DALException("There was no user with this ID");
		}
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserName().equals(userName)) {
				userList.remove(i);
			}
		}
		try {
			FileOutputStream saveFile = new FileOutputStream("users.sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(userList);
			save.close();
		} catch (IOException exc) {
			throw new DALException("There was an error trying to create a user");
		}
	}

}
