package functionality;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import data.UserDTO;

@Path("functionality")
public class Functionality  implements IFunctionality{

	public void changeUser(int id, String newName, String newPassword, String newIni) {
		
	}

	public void createUser(String name, String password, String ini, String cpr) {

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
	public String login(@FormParam("username") String usr, @FormParam("password") String pass) {
		if(!usr.equals("Admin") && !pass.equals("")) {
			return "Password er forkert"; 
		} else {
			return "Password er korrekt";
		}
	}

}
