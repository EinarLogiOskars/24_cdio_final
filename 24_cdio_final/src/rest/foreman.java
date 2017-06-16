package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import daoimplements.ProduktBatchDAO;
import daoimplements.ProduktBatchKompDAO;
import daoimplements.RaavareBatchDAO;
import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IProduktBatchDAO;
import daointerfaces.IProduktBatchKompDAO;
import daointerfaces.IRaavareBatchDAO;
import entity.ProduktBatchDTO;
import entity.ProduktBatchKompDTO;
import entity.RaavareBatchDTO;
import entity.ReceptKompDTO;

@Path("/foreman")
public class foreman {

	IProduktBatchDAO pb = new ProduktBatchDAO();
	IRaavareBatchDAO rb = new RaavareBatchDAO();
	IProduktBatchKompDAO pbk = new ProduktBatchKompDAO();
	

	
	/**
	 * 
	 * @param produktbatch
	 * @return true
	 */
	@POST
	@Path("/createproductbatch")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createProductBatch(ProduktBatchDTO produktbatch) {
		try {
			pb.createProduktBatch(produktbatch);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}
	
	@POST
	@Path("/pbkomp")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createPBKomp(ProduktBatchKompDTO pbKomp) {
		System.out.println(pbKomp.getPbId());
		try {
			pbk.createProduktBatchKomp(pbKomp);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * 
	 * @return productbatches
	 */
	@GET
	@Path("/showproductbatches")
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
	
	@GET
	@Path("/materialbatchID/{mbId}")
	@Produces(MediaType.APPLICATION_JSON)
	public RaavareBatchDTO getmbId(@PathParam("mbId") int mbId) {
		RaavareBatchDTO RaavareBatch = new RaavareBatchDTO();
		try {
			RaavareBatch = rb.getRaavareBatch(mbId);
		} catch (DALException e) { e.printStackTrace(); }
		return RaavareBatch;
	}
	
	@GET
	@Path("/productbatchID/{pbId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProduktBatchDTO getpbId(@PathParam("pbId") int pbId) {
		ProduktBatchDTO ProduktBatch = new ProduktBatchDTO();
		try {
			ProduktBatch = pb.getProduktBatch(pbId);
		} catch (DALException e) { e.printStackTrace(); }
		return ProduktBatch;
	}
	
}
