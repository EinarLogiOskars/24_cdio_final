package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import daoimplements.BrugerDAO;
import daointerfaces.IBrugerDAO;
import daointerfaces.IBrugerDAO.DALException;
import entity.BrugerDTO;

@Path("/login")
public class login {
	
	IBrugerDAO us = new BrugerDAO();
	
	@POST
	@Path("/logincheck")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean loginCheck(BrugerDTO user) {
		boolean validated = false;
		try {
			if(us.validateBruger(user)){validated = true; }
		} catch (DALException e) {e.printStackTrace(); }
		
		return validated;
	}
	
	@GET
	@Path("/{username}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String getRoles(@PathParam("username") String username) {
		String roles = "";
		
		try {roles = us.getRights(username);
		} catch (DALException e) {e.printStackTrace(); }
		return roles;
	}
	

}
