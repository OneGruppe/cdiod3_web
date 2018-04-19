package functionality;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import data.UserDTO;
import test.*;

@Path("functionality")
public class Functionality //implements IFunctionality{
	{

	public void changeUser(int id, String newName, String newPassword, String newIni) {

	}
	
	@POST
	@Path("createUser")
	public void createUser(@FormParam("username") String name, @FormParam("password") String password, @FormParam("ini") String ini, @FormParam("CPR") String cpr){
		System.out.println(name);
		System.out.println(password);
		System.out.println(ini);
		System.out.println(cpr);
		//System.out.println(role);
		
		//UserDTO user = new UserDTO(name, password, ini, cpr, role);
		//dao.createUser(user);
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

	@POST
	@Path("login")
	public boolean login(@FormParam("username") String usr, @FormParam("password") String pass, @FormParam("check1") String check1) {
		System.out.println(check1);
		TestCon con = new TestCon();
		con.doConnection();
		boolean isMatch = con.isUserAndPassCorrect(usr, pass);
		System.out.println("Funk " + isMatch);
		return isMatch;
	}
}
