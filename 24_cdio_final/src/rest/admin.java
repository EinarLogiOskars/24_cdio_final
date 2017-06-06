package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import daoimplements.BrugerDAO;
import daointerfaces.IBrugerDAO;
import daointerfaces.IBrugerDAO.DALException;
import entity.BrugerDTO;



@Path("/admin")
public class admin {


	private IBrugerDAO us = new BrugerDAO();

	@POST
	@Path("/createuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean newUser(BrugerDTO user){
		
		try {
			us.createBruger(user);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		return true;
	}

	
	@GET
	@Path("/userids")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Integer> getTakenIds() {
		ArrayList<Integer> userIds = new ArrayList<Integer>();
		try {
			userIds = us.getUserIds();
		} catch (DALException e) { e.printStackTrace(); }
		return userIds;
	}
	
	
	@GET
	@Path("/showusers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BrugerDTO> getUserList(){
		List<BrugerDTO> userList = new ArrayList<BrugerDTO>();
		try {
			userList = us.getBrugerList();
		} catch (DALException e) { e.printStackTrace(); }
		return userList;
	}
	
	
	@DELETE
	@Path("/delete/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean deleteUser(@PathParam("userId") int userId){
		try {
			us.deleteBruger(userId);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}
	
	
	@GET
	@Path("/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	public BrugerDTO getUser(@PathParam("userID") int userID){
		BrugerDTO user = new BrugerDTO();
		try {
			user = us.getBruger(userID);
		} catch (DALException e) { e.printStackTrace(); }
		
		return user;
	}
	
	
	@POST
	@Path("/updateuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateUser(BrugerDTO user){
		try {
			us.updateBruger(user);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}

}
