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
import daoimplements.ReceptDAO;
import daoimplements.ReceptKompDAO;
import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IRaavareDAO;
import daointerfaces.IReceptDAO;
import daointerfaces.IReceptKompDAO;
import entity.RaavareDTO;
import entity.ReceptDTO;
import entity.ReceptKompDTO;

@Path("/pharmacist")
public class pharmacist {
	
	IRaavareDAO rv = new RaavareDAO();
	IReceptDAO rp = new ReceptDAO();
	IReceptKompDAO rk = new ReceptKompDAO();
	
	
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
	
	//Added receipts create and show methods...
	
	@POST
	@Path("/createreceipt")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createReceipt(ReceptDTO recept) {
		try {
			rp.createRecept(recept);
		} catch (DALException e) { e.printStackTrace(); }
		return true;
	}
	
	@GET
	@Path("/showreceipts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReceptDTO> getReceiptList() {
		List<ReceptDTO> receipts = null;
		try {
			receipts = rp.getReceptList();
		} catch (DALException e) { e.printStackTrace(); }
		
		return receipts;
	}
	
	@GET
	@Path("/receiptid/{receiptName}")
	public int getReceiptId(@PathParam("receiptName") String name) {
		int id = 0;
		try {
			id = rp.getId(name);
		} catch (DALException e) { e.printStackTrace(); }
		return id;
	}
	
	@GET
	@Path("/{receiptId}")
	public ReceptDTO getReceipt(@PathParam("receipt") int receiptId) {

		ReceptDTO receipt = new ReceptDTO();
		try {
			receipt = rp.getRecept(receiptId);
		} catch (DALException e) { e.printStackTrace(); }
		return receipt;
	}
		
	
	@POST
	@Path("/createreceiptkomp")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createReceiptkomp(ReceptKompDTO receptKomp) {
		ReceptKompDTO reckomp = new ReceptKompDTO();
		try {
			rk.createReceptKomp(receptKomp);
			reckomp = rk.getReceptKomp(receptKomp.getReceptId(), receptKomp.getRaavareId());
		} catch (DALException e) { e.printStackTrace(); }
		System.out.println(reckomp.toString());
		
		return true;
	}

}
