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

import daoimplements.ProduktBatchDAO;
import daoimplements.RaavareBatchDAO;
import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IProduktBatchDAO;
import daointerfaces.IRaavareBatchDAO;
import entity.ProduktBatchDTO;
import entity.RaavareBatchDTO;
import entity.RaavareDTO;

public class foreman {

	IProduktBatchDAO pb = new ProduktBatchDAO();
	IRaavareBatchDAO rb = new RaavareBatchDAO();
	

	
	/**
	 * 
	 * @param produktbatch
	 * @return true
	 */
	@POST
	@Path("/createproduktbatch")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createProductBatch(ProduktBatchDTO produktbatch) {
		try {
			pb.createProduktBatch(produktbatch);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * 
	 * @return productbatches
	 */
	@GET
	@Path("/showproduktbatches")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProduktBatchDTO> getProductBatchList() {
		List<ProduktBatchDTO> productbatches = null;
		try {
			productbatches = pb.getProduktBatchList();
		} catch (DALException e) { e.printStackTrace(); }
		
		return productbatches;
	}
	
	/**
	 * 
	 * @param raavarebatch
	 * @return true
	 */
	@POST
	@Path("/creatematerialbatch")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createMaterialBatch(RaavareBatchDTO raavarebatch) {
		try {
			rb.createRaavareBatch(raavarebatch);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * 
	 * @return materialbatches
	 */
	@GET
	@Path("/showmaterialbatches")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RaavareBatchDTO> getMaterialBatchList() {
		List<RaavareBatchDTO> materialbatches = null;
		try {
			materialbatches = rb.getRaavareBatchList();
		} catch (DALException e) { e.printStackTrace(); }
		
		return materialbatches;
	}
	
}
