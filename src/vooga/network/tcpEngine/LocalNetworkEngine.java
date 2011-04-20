package vooga.network.tcpEngine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import vooga.network.INetworkEngine;

public class LocalNetworkEngine extends AbstractNetworkEngine  {
	
	public LocalNetworkEngine(int port)
	{
		super(port);
	}
	
	public LocalNetworkEngine()
	{
		super(Constants.HostPort);
	}
	
	
	public boolean createHost(boolean fullyConnect) {
		//only create host is not connected yet
		if (!isConnected){
			try {
				serverSocket = new ServerSocket(port, 10);
				// start to listen to the UDP broadcast
				FindHostBroadcastListener listener = new FindHostBroadcastListener();
				listener.start();
	
				ServerListener listenerThread = new ServerListener(serverSocket,
						this, fullyConnect);
				(new Thread(listenerThread)).start();
				isConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
				return true;
			}
			return false;
		}
		else
			return true;
	}

	@Override
	public List<ConnectInfo> searchHost() {
		// first boardcast UDP to all nearby computers
		FindHostBroadcast broadcastUDP = new FindHostBroadcast();
		broadcastUDP.start();
		DatagramSocket server = null;

		Set<String> availableServer = new HashSet<String>();
		List<ConnectInfo> availableServers = new ArrayList<ConnectInfo>();
		byte[] buffer = new byte[65507];

		try {
			server = new DatagramSocket(Constants.BroadcastResponsePort);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			server.setSoTimeout(Constants.SearchHostTimeOut);

			//in the while loop, wait for time out
			while (true) {
				server.receive(packet);
				String message = new String(packet.getData(), 0,
						packet.getLength());
				if (message.split(",").length < 2)
					continue;
				String token = message.split(",")[0];
				String serverIP = message.split(",")[1];
				if (token.equals(Constants.BroadcastResponsePrefix)
						&& !availableServer.contains(serverIP)) {
					System.out.println("returned from sever" + serverIP);
					availableServer.add(serverIP);
					availableServers.add(new ConnectInfo(serverIP, myInfo
							.getName()));
				}
			}
		} catch (Exception e) {
			//System.out.println("exception occur");
			//e.printStackTrace();
		} finally {
			server.close();
		}
		return availableServers;
	}
}
