package functionality;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

import data.*;
import data.IUserDAO.*;

@Path("functionality")
public class Functionality //implements IFunctionality{
{
	UserDAO dao;

	public Functionality() {
		UserDAO dao = new UserDAO();
		this.dao = dao;		
		dao.doConnection();
	}

	@POST
	@Path("login")
	public boolean login(@FormParam("username") String usr, @FormParam("password") String pass) {
		//boolean isMatch = dao.isUserAndPassCorrect(usr, pass);
		boolean isMatch = false;
		UserDTO user = null;
		try {
			user = dao.getUser(usr);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user.getPassword().equals(pass)) {isMatch = true;}
		System.out.println("Funktionality-login: " + isMatch);
		return isMatch;
	}

	@POST
	@Path("createUser")
	public String createUser(@FormParam("username") String name, @FormParam("password") String password, @FormParam("ini") String ini, @FormParam("CPR") String cpr, @FormParam("admin") boolean admin, @FormParam("laborant") boolean laborant, @FormParam("farmaceut") boolean farmaceut, @FormParam("produktionsleder") boolean produktionsleder){
		String returnValue = "Error in src/functionality/functionality/createUser";
		List<String> roleList = new ArrayList<String>();
		List<UserDTO> DTOList = null;
		try {
			DTOList = dao.getUserList();
		} catch (DALException e1) {
			e1.printStackTrace();
		}

		for (int j = 0; j < DTOList.size(); j++) {
			if (DTOList.get(j).getUserName().equals(name) || DTOList.get(j).getIni().equals(ini) || DTOList.get(j).getCpr().equals(cpr)) {
				return "Username, ini or CPR already exists";
			}
		}

		System.out.println("----***** FUNK: CreateUser: *****----");
		System.out.println("Name: " + name);
		System.out.println("Password: " + password);
		System.out.println("Initials: " + ini);
		System.out.println("CPR: " + cpr);
		System.out.println("Admin: " + admin);
		System.out.println("Laborant: " + laborant);
		System.out.println("Farmaceut: " + farmaceut);
		System.out.println("Produktionsleder: " + produktionsleder + "\n");

		if(admin) {roleList.add("1");}
		if(laborant) {roleList.add("2");}
		if(farmaceut) {roleList.add("3");}
		if(produktionsleder) {roleList.add("4");}

		System.out.println("RoleList:");
		for(String role : roleList) {
			System.out.println(role);
		}
		System.out.println("\n ----***** FUNK: CreateUser end *****----");
		if (!cpr.matches("\\d{6}\\-\\d{4}")) {return "CPR does not match 6 digits dash 4 digits";}
		if (!name.matches("[a-åA-Å0-9]{4,20}$")) {return "Username does not match a-å, A-Å or 0-9 while being between 4 and 20 characters";}
		if (!password.matches("[a-åA-Å0-9]{4,20}$")) {return "Password does not match a-å, A-Å or 0-9 while being between 4 and 20 characters";}
		if (!ini.matches("[a-åA-Å]{1,3}$")) {return "Initials does not match a-å or A-Å while being bewteen 1 and 3 characters";}
		
		if (cpr.matches("\\d{6}\\-\\d{4}") && name.matches("[a-åA-Å0-9]{4,20}$") && password.matches("[a-åA-Å0-9]{4,20}$") && ini.matches("[a-åA-Å]{1,3}$")) {
			System.out.println("Pikslikker");
			UserDTO newUser = new UserDTO(0, name, password, ini, cpr, roleList);
			try {
				dao.createUser(newUser);
				returnValue = "User successfully created";
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		else {
			returnValue = "User not created, try again";
		}
		return returnValue;
	}

	public void changeUser(int id, String newName, String newPassword, String newIni) {

	}

	public String deleteUser(@FormParam("username") String userName) {
		String returnString = null;
		try {
			List<UserDTO> DTOList = dao.getUserList();
			for(int i = 0; i < DTOList.size(); i++) {
				if(DTOList.get(i).getUserName().equals(userName)) {
					dao.deleteUser(userName);
					System.out.println("User " + userName + " was deleted!");
					returnString = "User " + userName + " blev slettet!";
					return returnString;
				}
			}
		} catch (DALException e) {
			e.printStackTrace();
			
		}	
		return returnString;
	}

	@POST
	@Path("showUser")
	public String showUser(@FormParam("username") String name) {
		String retur = "'" + name + "' har været igennem Java, og vises nu her.";
				System.out.println(retur);
		return retur;
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
