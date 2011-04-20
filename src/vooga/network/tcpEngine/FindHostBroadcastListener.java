package vooga.network.tcpEngine;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class FindHostBroadcastListener extends Thread {
	byte[] buffer = new byte[65507];
	DatagramSocket server = null;
	DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	
	
	public void run(){

		try {
			InetAddress serverAddress = InetAddress.getLocalHost();
			server = new DatagramSocket(Constants.BroadcastPort);
			//TODO: test print to be deleted
			//System.out.println(serverAddress.getHostAddress());
			while (true) {
				server.receive(packet);
				String message = new String(packet.getData(), 0, packet.getLength());
				
				System.out.println(message);
				
				if (message.split(",").length < 2)
					continue;
				String token = message.split(",")[0];
				String clientIP = message.split(",")[1];
				String response = Constants.BroadcastResponsePrefix + "," + serverAddress.getHostAddress();
				byte data[] = response.getBytes();
				if (token.equals(Constants.BroadcastPrefix)){
					packet = new DatagramPacket(data, data.length, InetAddress.getByName(clientIP), Constants.BroadcastResponsePort);
					server.send(packet); 
					//System.out.println("return address: "+response);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
