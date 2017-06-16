package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import socket.ISocketController;
import socket.SocketController;
import socketController.IMainController;
import socketController.MainController;

@Path("/connection")
public class socketConnection {
	
	@GET
	@Path("/start")
	public boolean startConnection() {
		ISocketController socketController = new SocketController();
		IMainController mainController = new MainController(socketController);
		mainController.start();	
		
		return true;
	}
	
}
