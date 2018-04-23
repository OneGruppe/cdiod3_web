package functionality;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import data.IUserDAO.DALException;
import data.*;


@Path("functionality")
public class Functionality //implements IFunctionality{
{
	IUserDAO dao;
	boolean isoffline = false;

	public Functionality() {
		UserDAO dao;
		OfflineUserDAO offdao = null;
		try {
			dao = new UserDAO();
			offdao = new OfflineUserDAO();
			if(dao.doConnection()) {
				this.dao = dao;
				System.out.println("\n** Server is online **\n");
				offdao.saveToFile(dao.getUserListBackup());
				System.out.println("\n**- DATABASE-BACKUP SAVED -**\n");
			}
		} catch (DALException e) {
			System.out.println(e.getMessage());
			try {
				offdao = new OfflineUserDAO();
				this.dao = offdao;
				isoffline = true;
				System.out.println("\n** Server is offline **\n");
			} catch (DALException e1) {
				System.out.println(e.getMessage());
			}
		}
	}

	@POST
	@Path("login")
	public String login(@FormParam("username") String usr, @FormParam("password") String pass) {
		String isMatch = "false";
		UserDTO user = null;
		try {
			user = dao.getUser(usr);
			if (user.getUserName() == null) {return isMatch;}
			else if (user.getPassword().equals(pass)) {
				for (String role : user.getRoles()) {
					if (role.equals("1")){isMatch = "true";}
				}
			}
			System.out.println("\nLOGIN: UserName: " + usr + ", password: " + pass + ", does match? = " + isMatch);
			System.out.println("\n");
			return isMatch;
		} catch (DALException e) {
			return isMatch;
		}
	}

	@POST
	@Path("logout")
	public boolean logout() {
		if(isoffline== false){
			try {
				dao.getUser("admin");
				return true;
			} catch (DALException e) {
				System.out.println("Logout error: " + e.getMessage());
				return false;
			}
		} else{
			return true;
		}
	}

	@POST
	@Path("createUser")
	public String createUser(@FormParam("username") String name, @FormParam("password") String password, @FormParam("ini") String ini, @FormParam("CPR") String cpr, @FormParam("admin") boolean admin, @FormParam("laborant") boolean laborant, @FormParam("farmaceut") boolean farmaceut, @FormParam("produktionsleder") boolean produktionsleder){
		if(this.isoffline) {return "External server is offline, you cannot create / edit users";}
		String returnValue = "Error in src/functionality/functionality/createUser";
		List<String> roleList = new ArrayList<String>();

		List<UserDTO> existingUsers = null;
		try {
			existingUsers = dao.getUserList();
			for(UserDTO usr : existingUsers) {
				if(usr.getUserName().equals(name) ) {	return "Username already exist, try again";}
				if(usr.getIni().equals(ini) ) {	return "Ini already exist, try again";}
				if(usr.getCpr().equals(cpr) ) {	return "CPR-number already exist, try again";}
			}
		}catch (DALException e1) {
			System.out.println(e1.getMessage());
			System.out.println("\n");
		}

		if(admin) {roleList.add("1");}
		if(laborant) {roleList.add("2");}
		if(farmaceut) {roleList.add("3");}
		if(produktionsleder) {roleList.add("4");}

		if (!name.matches("[\\w]{4,20}$")) {return "Username does not match a-å or 0-9 while being between 4 and 20 characters";}
		if (name.equals("admin") || name.equals("Admin")) {return "Invalid username";}
		if (!password.matches("[\\w]{4,20}$")) {return "Password does not match a-å or 0-9 while being between 4 and 20 characters";}
		if (!ini.matches("[\\w]{1,3}$")) {return "Initials does not match a-å while being bewteen 1 and 3 characters";}
		if (!cpr.matches("\\d{6}\\-\\d{4}")) {return "CPR does not match 6 digits dash 4 digits";}
		if(roleList.isEmpty()) {
			return "You have to choose at least one user role";
		}

		if (cpr.matches("\\d{6}\\-\\d{4}") && name.matches("[\\w]{4,20}$") && password.matches("[\\w]{4,20}$") && ini.matches("[\\w]{1,3}$")) {
			UserDTO newUser = new UserDTO(0, name, password, ini, cpr, roleList);
			try {
				dao.createUser(newUser);
				returnValue = "User successfully created";
			} catch (DALException e) {
				System.out.println(e.getMessage());
			}
		}
		return returnValue;
	}

	@POST
	@Path("updateUser")
	public String changeUser(@FormParam("username") String userName, @FormParam("newName") String newName, @FormParam("newPassword") String newPassword, @FormParam("newIni") String newIni) {
		if(this.isoffline) {return "External server is offline, you cannot create / edit users";}
		String returnString = "'" + userName + "' doesn't exist";
		if(userName.equals("admin")) {
			return "Invalid input";
		}
		List<UserDTO> existingUsers = null;
		try {
			existingUsers = dao.getUserList();
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(UserDTO usr : existingUsers) {
			if(usr.getUserName().equals(newName) ) {return "Username already exist, try again";}
			if(usr.getIni().equals(newIni) ) {return "Ini already exist, try again";}
			//Skal der være mulighed for at ændre CPR?
			//if(usr.getCpr().equals(newCpr) ) {	return "CPR-number already exist, try again";}
		}

		if (!newName.matches("[\\w]{4,20}$")) {return "Username does not match a-å or 0-9 while being between 4 and 20 characters";}
		if (newName.equals("admin") || newName.equals("Admin")) {return "Invalid username";}
		if (!newPassword.matches("[\\w]{4,20}$")) {return "Password does not match a-å or 0-9 while being between 4 and 20 characters";}
		if (!newIni.matches("[\\w]{1,3}$")) {return "Initials does not match a-å while being bewteen 1 and 3 characters";}
		//if (!newCpr.matches("[\\d{6}\\-\\d{4}")) {return "CPR does not match 6 digits dash 4 digits";}

		try {
			UserDTO user = dao.getUser(userName);

			user.setUserName(newName);
			user.setPassword(newPassword);
			user.setIni(newIni);

			dao.updateUser(user);

			returnString = "User " +userName+ " was succesfully modified";
			return returnString;

		} catch (DALException e) {
			System.out.println(e.getMessage());
			return "An error occurred";
		}
	}

	@POST
	@Path("deleteUser")
	public String deleteUser(@FormParam("username") String userName) {
		if(this.isoffline) {return "External server is offline, you cannot create / edit users";}
		String returnString = "'" + userName + "' doesn't exist";
		if(userName.equals("admin")) {
			return "Invalid input";
		}
		try {
			List<UserDTO> DTOList = dao.getUserList();
			for(int i = 0; i < DTOList.size(); i++) {
				if(DTOList.get(i).getUserName().equals(userName)) {
					dao.deleteUser(userName);
					returnString = "User " + userName + " was deleted";
					return returnString;
				}
			}
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println("\n");
			return "An error occurred";
		}
		return returnString;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("showUser")
	public String showUser(@FormParam("username") String name) {
		JSONObject userJSON = new JSONObject();
		try {			
			UserDTO user = dao.getUser(name);

			userJSON.put("user_id",user.getUser_id());
			userJSON.put("name", user.getUserName());
			userJSON.put("password", user.getPassword());
			userJSON.put("ini", user.getIni());
			userJSON.put("cpr", user.getCpr());
			userJSON.put("roles", user.getRoles());

			System.out.println("Brugerens information er fundet:");
			System.out.println(userJSON);


		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n");
		return userJSON.toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("showAllUsers")
	public String showUserList() {
		JSONArray userArray = new JSONArray();
		try {
			userArray.put(dao.getUserList());
		}
		catch (DALException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(userArray);
		return userArray.toString();
	}

}
