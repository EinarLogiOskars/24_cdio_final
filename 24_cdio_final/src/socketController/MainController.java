package socketController;



import java.util.ArrayList;
import java.util.List;

import daoimplements.BrugerDAO;
import daoimplements.ProduktBatchDAO;
import daoimplements.ProduktBatchKompDAO;
import daoimplements.RaavareBatchDAO;
import daoimplements.RaavareDAO;
import daoimplements.ReceptDAO;
import daoimplements.ReceptKompDAO;
import daointerfaces.IBrugerDAO;
import daointerfaces.IBrugerDAO.DALException;
import daointerfaces.IProduktBatchDAO;
import daointerfaces.IProduktBatchKompDAO;
import daointerfaces.IRaavareBatchDAO;
import daointerfaces.IRaavareDAO;
import daointerfaces.IReceptDAO;
import daointerfaces.IReceptKompDAO;
import entity.BrugerDTO;
import entity.ProduktBatchDTO;
import entity.ProduktBatchKompDTO;
import entity.RaavareBatchDTO;
import entity.RaavareDTO;
import entity.ReceptDTO;
import entity.ReceptKompDTO;
import socket.ISocketController;
import socket.NoInputFoundException;

public class MainController implements IMainController {

	private ISocketController socketController;
	private IBrugerDAO us;
	private IRaavareBatchDAO rbdao;
	private IProduktBatchDAO pbdao;
	private IReceptDAO rdao;
	private IProduktBatchKompDAO pbkdao;
	private IReceptKompDAO rkdao;
	private IRaavareDAO mdao;
	String id;

	public MainController(ISocketController socketController) {
		this.socketController = socketController;
	}

	public void start(){

		go();

	}

	public void go() {
		//DAOs
		us = new BrugerDAO();
		rbdao = new RaavareBatchDAO();
		pbdao = new ProduktBatchDAO();
		mdao = new RaavareDAO();
		rdao = new ReceptDAO();
		rkdao = new ReceptKompDAO();
		pbkdao = new ProduktBatchKompDAO();
		
		//DTOs
		BrugerDTO user = new BrugerDTO();
		RaavareBatchDTO rb = new RaavareBatchDTO();
		ProduktBatchDTO pb = new ProduktBatchDTO();
		ProduktBatchKompDTO pbk = new ProduktBatchKompDTO();
		List<ProduktBatchKompDTO> components = new ArrayList<ProduktBatchKompDTO>();
		ReceptDTO r = new ReceptDTO();
		ReceptKompDTO rk = new ReceptKompDTO();
		



		//Change keystate to 4...
		sequence("K 4");

		try {
			user = us.getBruger(Integer.parseInt(sequence("RM208 Enter your user ID")));
		} catch (NumberFormatException | DALException e) { e.printStackTrace(); }
		System.out.println(user.getUserName());
		
		sendMessage("RM208 Welcome " + user.getUserName() + ", please wait..."); 
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) { e1.printStackTrace(); }

		try {
			pb = pbdao.getProduktBatch((Integer.parseInt(sequence("RM208 Enter product batch id"))));
		} catch (NumberFormatException | DALException e) { e.printStackTrace(); }

		try {
			sendMessage("RM208 You are producing: " + rdao.getRecept(pb.getReceptId()).getReceptNavn() + ", please wait...");
		} catch (DALException e) { e.printStackTrace(); }
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) { e1.printStackTrace(); }
		
		//Get all the components for this product batch...
		try {
			components = pbkdao.getProduktBatchKompList(pb.getPbId());
		} catch (DALException e1) { e1.printStackTrace(); }
		
		
		//For each loop - to run through each component in the specific product batch...
		for (ProduktBatchKompDTO comp : components) {
			
			Double weight = 0.0;
			
			RaavareDTO material = new RaavareDTO();
			
			sendMessage("RM208 Check if weight is unloaded");
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) { e1.printStackTrace(); }
			
			//Update status and update status in database (try catch clause)...
			pb.setStatus(1);
			try {
				pbdao.updateProduktBatch(pb);
			} catch (DALException e) { e.printStackTrace(); }
			
			sequence("RM208 Tare the weight");
			
			sequence("RM208 Place tara container on the weight");	
			
			comp.setTara(Double.parseDouble(sequence("RM208 Tara the weight container")));
			
			//Update with tared weight from the container...
			try {
				comp.setOprId(user.getUserId());
				pbkdao.updateProduktBatchKomp(comp);
			} catch (DALException e) { e.printStackTrace(); }
			
			try {
				pbk = pbkdao.getProduktBatchKomp(pb.getPbId(),Integer.parseInt(sequence("RM208 Enter Materialbatch id")));
			} catch (NumberFormatException | DALException e) { e.printStackTrace(); }
			
			try {
				rk = rkdao.getReceptKomp(pb.getReceptId(), rbdao.getRaavareBatch(pbk.getRbId()).getRaavareId());
				do {
					weight = Double.parseDouble(sequence("RM208 Weight the material: " + mdao.getRaavare(rbdao.getRaavareBatch(pbk.getRbId()).getRaavareId()).getRaavareNavn()));
				} while (rk.getNomNetto() - (rk.getNomNetto() * rk.getTolerance()) > weight && rk.getNomNetto() + (rk.getNomNetto() * rk.getTolerance()) < weight); //Within tolerance of weight...
				comp.setNetto(weight);
				pbkdao.updateProduktBatchKomp(comp);
			} catch (DALException e1) { e1.printStackTrace(); }
			
		}  //End of for each loop
		
		sendMessage("RM208 Product batch complete. Press Exit to quit.");
		pb.setStatus(2);
		try {
			pbdao.updateProduktBatch(pb);
		} catch (DALException e) { e.printStackTrace(); }
		
	}

	private String sequence(String str) {
		socketController.connect();
		socketController.sendMessage(str);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) { e1.printStackTrace(); }

		String answer = "";
		try {
			answer = socketController.receiveMessage();
			System.out.println("answer is: " + answer);
		} catch (NoInputFoundException e) {
			e.printStackTrace();
		}
		socketController.closeConnection();
		return answer;
	}

	private void sendMessage(String str) {
		socketController.connect();
		socketController.sendMessage(str);
		socketController.closeConnection();
	}



	//1.Laboranten har modtaget en produktionsforskrift p� papir fra v�rkf�reren.
	//
	//2.Laboranten v�lger en afvejningsterminal.
	//
	//3.Laboranten indtaster laborant nr.
	//
	//4.V�gten svarer tilbage med laborantnavn som s� godkendes.
	//
	//5.Laboranten indtaster produktbatch nummer.
	//
	//6 V�gten svarer tilbage med navn p� recept der skal produceres (eks: saltvand med citron)
	//
	//7.Laboranten kontrollerer at v�gten er ubelastet og trykker �ok�
	//
	//
	//9.V�gten tareres.
	//
	//10.V�gten beder om f�rste tara beholder.
	//
	//11.Laborant placerer f�rste tarabeholder og trykker �ok�
	//
	//12.V�gten af tarabeholder registreres
	//
	//13.V�gten tareres.
	//
	//14.V�gten beder om raavarebatch nummer p� f�rste r�vare.
	//
	//15.Laboranten afvejer op til den �nskede m�ngde og trykker �ok�
	//
	//16.Pkt. 7 � 14 gentages indtil alle r�varer er afvejet.
	//
	//8.Systemet s�tter produktbatch nummerets status til �Under produktion�.
	//17.Systemet s�tter produktbatch nummerets status til �Afsluttet�.
	//
	//18.Det kan herefter genoptages af en ny laborant.
	//


}
