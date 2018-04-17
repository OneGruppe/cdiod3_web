package test;

public class TestMain {

	public static void main(String[] args) {
		//Lav objekt af testConnection
		TestCon con = new TestCon();
		
		// Forbindelse
		if(con.doConnection()) {
		System.out.println("Connection is established");
		}else {
			System.out.println("No connection");
		}
		
		//Print rooms in building
		for(String room : con.showListOfRoles()) {
			System.out.println(room);
		}
	}
}
