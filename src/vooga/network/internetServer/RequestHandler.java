package vooga.network.internetServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Set;

import vooga.network.tcpEngine.ConnectionControl;
import vooga.network.tcpEngine.Constants;

public class RequestHandler extends Thread {
	Socket client;
	Set<String> availableServers;
	ObjectOutputStream output = null;
	ObjectInputStream input = null;
	
	RequestHandler(Socket client, Set<String> availableServers){
		this.client = client;
		this.availableServers = availableServers;
	}
	public void run(){
		try{
			try{
				output = new ObjectOutputStream(client.getOutputStream());
				output.flush();
				input = new ObjectInputStream(client.getInputStream());
				Object obj = input.readObject();
				String incomingMsg = (String) obj;
				if (incomingMsg.equals(Constants.RequestAvailableHostPrefix)){
					//output.writeObject(Constants.ResponseAvailableHostPrefix);
					for (String ip : availableServers){
						output.writeObject(ip);
						output.flush();
					}
					output.writeObject(ConnectionControl.STOP);
				}
				else{
					if (incomingMsg.split(",").length < 2)
						return;
					String prefix = incomingMsg.split(",")[0];
					String hostIP = incomingMsg.split(",")[1];
					if (prefix.equals(Constants.CreateHostPrefix)){
						availableServers.add(hostIP);
						System.out.println("add host to server: " + hostIP);
					}
					if (prefix.equals(Constants.DisconnectHostPrefix))
						availableServers.remove(hostIP);
					client.close();
				}
				output.flush();
			}
			finally{
				//output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
