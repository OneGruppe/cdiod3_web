package test;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("test_database")
public class TestMain {

	@GET
	public String getRoller() {

		// Lav objekt af testConnection
		TestCon con = new TestCon();

		String besked;
		// Forbindelse
		if (con.doConnection()) {
			besked = "Connection is established: ";
		} else {
			besked = "No connection: ";
		}

		ArrayList<String> roles = con.showListOfRoles();

		return besked + "<br><br>" + roles.toString();
	}
}
