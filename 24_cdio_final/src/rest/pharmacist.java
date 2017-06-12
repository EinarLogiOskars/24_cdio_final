package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import daoimplements.RaavareDAO;
import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IRaavareDAO;
import entity.RaavareDTO;

@Path("/pharmacist")
public class pharmacist {
	
	IRaavareDAO rv = new RaavareDAO();
	
	
	@POST
	@Path("/createmat")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createMat(RaavareDTO mat) {
		try {
			rv.createRaavare(mat);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}
	
	@GET
	@Path("/showmats")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RaavareDTO> getMatList() {
		List<RaavareDTO> mats = null;
		try {
			mats = rv.getRaavareList();
		} catch (DALException e) { e.printStackTrace(); }
		
		return mats;
	}
	
	@POST
	@Path("/updatemat")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateMat(RaavareDTO mat) {
		try {
			rv.updateRaavare(mat);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}
	
	@GET
	@Path("/matids")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getMatIds() {
		List<Integer> matIds = new ArrayList<Integer>();
		List<RaavareDTO> mats = null;
		try {
			mats = rv.getRaavareList();
		} catch (DALException e) { e.printStackTrace(); }
		for(RaavareDTO m: mats){
			matIds.add(m.getRaavareId());
		}
		return matIds;
	}
	
	@GET
	@Path("/{matId}")
	@Produces(MediaType.APPLICATION_JSON)
	public RaavareDTO getMat(@PathParam("matId") int matId) {
		RaavareDTO mat = new RaavareDTO();
		try {
			mat = rv.getRaavare(matId);
		} catch (DALException e) { e.printStackTrace(); }
		return mat;
	}

}
