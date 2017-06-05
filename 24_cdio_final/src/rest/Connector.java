package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bla")
public class Connector {
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello from backend";
	}
	
}
