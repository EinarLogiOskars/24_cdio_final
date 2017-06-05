package rest;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/final")
public class Connector {
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello from backend";
	}
	
	@GET
	@Path("/otherside")
	@Produces(MediaType.TEXT_PLAIN)
	public String otherside() {
		return "Hello from the othersiiiiiiide!";
	}
	
}
