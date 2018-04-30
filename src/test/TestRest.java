package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("test")
public class TestRest {

	@GET
	@Path("hello")
	public String helloTest() {
		return "Hello Test!";
	}
}
