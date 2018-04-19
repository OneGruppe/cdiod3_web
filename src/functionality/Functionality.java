package functionality;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

import data.*;
import data.IUserDAO.DALException;
import test.*;

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
	public void createUser(@FormParam("username") String name, @FormParam("password") String password, @FormParam("ini") String ini, @FormParam("CPR") String cpr, @FormParam("admin") boolean admin, @FormParam("laborant") boolean laborant, @FormParam("farmaceut") boolean farmaceut, @FormParam("produktionsleder") boolean produktionsleder){
		List<String> roleList = new ArrayList<String>();
		
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
				
		UserDTO newUser = new UserDTO(0, name, password, ini, cpr, roleList);
		try {
			dao.createUser(newUser);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		*/
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
