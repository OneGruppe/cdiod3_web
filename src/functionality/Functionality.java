package functionality;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

import data.*;
import data.IUserDAO.DALException;

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
			System.out.println("Shit works yo");
		} catch (DALException e1) {
			e1.printStackTrace();
		}

		for (int j = 0; j < DTOList.size(); j++) {
			System.out.println("Checking if " + name + " is equal to " + DTOList.get(j).getUserName());
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

		if (cpr.matches("\\d{6}\\-\\d{4}") && !name.equals(null) && !password.equals(null) && !ini.equals(null)) {
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

	public void deleteUser(int id) {

	}

	public UserDTO showUser(int id) {

		return null;
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
