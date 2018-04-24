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

	private static final long serialVersionUID = -6910394502110880451L;

	public void saveToFile(List<UserDTO> userlist) throws DALException {
		List<UserDTO> saveUserList = null;

		try {
			if(userlist.isEmpty()) {
				saveUserList = getUserList();
			} else { 
				saveUserList = userlist;
				}
			FileOutputStream saveFile = new FileOutputStream("users.sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(saveUserList);
			save.close(); 
		} catch (IOException | DALException e) {
			throw new DALException("Error in saveToFile: " + e.getMessage());
		}
	}

	@Override
	public UserDTO getUser(String userName) throws DALException {
		// Create the data objects for us to restore.
		List<UserDTO> userList = new ArrayList<UserDTO>();
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
				return usr;
			}
		}
		user = new UserDTO(0, null, null, null, null, null);
		return user;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		// Create the data objects for us to restore.
		List<UserDTO> userList = new ArrayList<UserDTO>();
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
		List<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			userList = getUserList();
		} catch (DALException e) {
			userList = new ArrayList<UserDTO>();
		} finally {
			userList.add(user.getUser_id()-1, user);;
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

	@Override
	public void updateUser(UserDTO user) throws DALException {
		List<UserDTO> userList = new ArrayList<UserDTO>();
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
		List<UserDTO> userList = new ArrayList<UserDTO>();
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
