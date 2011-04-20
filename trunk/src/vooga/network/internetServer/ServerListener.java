package vooga.network.internetServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Set;


public class ServerListener extends Thread {
	ServerSocket serverSocket;
	Set<String> availableServers;
	Socket client;
	Server server;
	
	
	ServerListener(ServerSocket serverSocket, Set<String> availableServers, Server server){
		this.serverSocket = serverSocket;
		this.availableServers = availableServers;
		this.server = server;
	}
	
	public void run(){
		try {
			while(!server.threadStop){
				client = serverSocket.accept();
				RequestHandler handler = new RequestHandler(client, availableServers);
				handler.start();
			}
		} catch (IOException e) {
			//TODO: exception
		}
	}
}
