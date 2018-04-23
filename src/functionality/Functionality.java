package functionality;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import data.IUserDAO.DALException;
import data.UserDAO;
import data.UserDTO;


@Path("functionality")
public class Functionality //implements IFunctionality{
{
	UserDAO dao;

	public Functionality() {
		UserDAO dao = new UserDAO();
		this.dao = dao;
		try {
			dao.doConnection();
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
	}

	@POST
	@Path("login")
	public boolean login(@FormParam("username") String usr, @FormParam("password") String pass) {
		System.out.println("------------------FUNCTIONALITY--login()-----------------");
		boolean isMatch = false;
		UserDTO user = null;
		try {
			user = dao.getUser(usr);
			if (user.getUserName() == null) {return isMatch;}
			else if (user.getPassword().equals(pass)) {
				for (String role : user.getRoles()) {
					if (role.equals("1")){isMatch = true;}
				}
			}
			System.out.println("UserName: " + usr + ", password: " + pass + ", does match? = " + isMatch);
			System.out.println("\n");
			return isMatch;
		} catch (DALException e) {
			System.out.println("An error occurred: " + e.getMessage());
			System.out.println("\n");
			return isMatch;
		}
	}

	@POST
	@Path("createUser")
	public String createUser(@FormParam("username") String name, @FormParam("password") String password, @FormParam("ini") String ini, @FormParam("CPR") String cpr, @FormParam("admin") boolean admin, @FormParam("laborant") boolean laborant, @FormParam("farmaceut") boolean farmaceut, @FormParam("produktionsleder") boolean produktionsleder){
		System.out.println("------------------FUNCTIONALITY--createUser()------------------");
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

		System.out.println("RoleList:");
		for(String role : roleList) {
			System.out.println(role);
		}
		if (!name.matches("[a-åA-Å\\w]{4,20}$")) {return "Username does not match a-å or 0-9 while being between 4 and 20 characters";}
		if (name.equals("admin") || name.equals("Admin")) {return "Invalid username";}
		if (!password.matches("[a-åA-Å\\w]{4,20}$")) {return "Password does not match a-å or 0-9 while being between 4 and 20 characters";}
		if (!ini.matches("[a-åA-Å\\w]{1,3}$")) {return "Initials does not match a-å while being bewteen 1 and 3 characters";}
		if (!cpr.matches("\\d{6}\\-\\d{4}")) {return "CPR does not match 6 digits dash 4 digits";}
		if(roleList.isEmpty()) {
			return "You have to choose at least one user role";
		}

		if (cpr.matches("\\d{6}\\-\\d{4}") && name.matches("[\\w]{4,20}$") && password.matches("[a-åA-Å\\w]{4,20}$") && ini.matches("[\\w]{1,3}$")) {
			UserDTO newUser = new UserDTO(0, name, password, ini, cpr, roleList);
			try {
				dao.createUser(newUser);
				returnValue = "User successfully created";
			} catch (DALException e) {
				System.out.println(e.getMessage());
				System.out.println("\n");
			}
		}

		System.out.println("\n");
			return returnValue;
		}
	
	@POST
	@Path("updateUser")
	public String changeUser(@FormParam("username") String userName, @FormParam("newName") String newName, @FormParam("newPassword") String newPassword, @FormParam("newIni") String newIni) {
		System.out.println("------------------FUNCTIONALITY--updateUser()------------------");
		String returnString = "'" + userName + "' doesn't exist";
		if(userName.equals("admin")) {
			return "Invalid input";
		}
		try {
			UserDTO user = dao.getUser(userName);
			System.out.println("Found user " +userName+ "\n");		
			
			user.setUserName(newName);
			user.setPassword(newPassword);
			user.setIni(newIni);
			
			dao.updateUser(user);
			
			System.out.println("\nUser " +userName+ " was modified to " +user.getUserName());
			
			returnString = "User " +userName+ " was succesfully modified";
			System.out.println("\n");
			return returnString;
			
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println("\n");
			return "An error occurred";
		}
	}

	@POST
	@Path("deleteUser")
	public String deleteUser(@FormParam("username") String userName) {
		System.out.println("------------------FUNCTIONALITY--deleteUser()------------------");
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
					System.out.println("\n");
					return returnString;
				}
			}
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println("\n");
			return "An error occurred";
		}
		System.out.println("\n");
		return returnString;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("showUser")
	public String showUser(@FormParam("username") String name) {
		System.out.println("------------------FUNCTIONALITY--showUser()------------------");
		JSONObject userJSON = new JSONObject();
		
		try {			
			UserDTO user = dao.getUser(name);
			
			userJSON.put("user_id",user.getUserId());
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

	public String showUserAdmin(int id) {

		return null;
	}

	public String showUserList() {

		return null;
	}


	public String showUserListAdmin() {

		return null;
	}

}
