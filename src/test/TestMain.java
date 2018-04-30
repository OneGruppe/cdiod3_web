package test;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import data.IUserDAO.DALException;

@Path("test_database")
public class TestMain {

	/**
	 * Viser rollerne på hjemmeside med GET metode
	 */
	@GET
	@Path("getRoles")
	public String getRoles() {

		// Lav objekt af testConnection
		TestCon con = new TestCon();

		String besked;
		// Forbindelse
		try {
			con.doConnection();
			besked = "Connection is established: ";
		} catch (DALException e) {
			return "No connection: ";
		}
		ArrayList<String> roles = con.showListOfRoles();
		return besked + "<br><br>" + roles.toString();
	}
}
