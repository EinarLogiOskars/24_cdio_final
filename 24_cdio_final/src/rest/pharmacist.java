package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	public void createMat(RaavareDTO mat) {
		try {
			rv.createRaavare(mat);
		} catch (DALException e) {e.printStackTrace(); }
	}
	
	@GET
	@Path("/showmats")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RaavareDTO> getMatList() {
		List<RaavareDTO> mats = null;
		try {
			mats = rv.getRaavareList();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mats;
	}

}
