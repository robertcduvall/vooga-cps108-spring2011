package vooga.network.internetServer;

import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import vooga.network.tcpEngine.Constants;

public class CheckConnectivity extends Thread {
	Socket checkConnect;
	Server server;
	Set<String> availableServers;
	Set<String> toBeDeleted;
	
	
	public CheckConnectivity(Set<String> availableServers, Server server){
		this.availableServers = availableServers;
		this.server = server;
	}
	
	public void run(){
		while (!server.threadStop){
			toBeDeleted = new HashSet<String>();
			for (String server : availableServers){
				try{
					System.out.println("available server: " + server);
					checkConnect = new Socket(server, Constants.HostRemainConnectivityPort);
					checkConnect.setSoTimeout(500);
					checkConnect.close();
				}
				catch(Exception e){
					System.out.println("Connect time out, delete host ip");
					toBeDeleted.add(server);
				}
			}
			//if (toBeDeleted.size() != 0)
			//	System.out.println("deleting...");
			for (String server : toBeDeleted)
				availableServers.remove(server);
			try {
				sleep(2000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("check server thread running: " + !server.threadStop);
		}
	}
}
