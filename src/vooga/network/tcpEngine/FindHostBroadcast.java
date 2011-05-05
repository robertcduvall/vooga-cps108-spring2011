package vooga.network.tcpEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FindHostBroadcast extends Thread {
	
	public void run(){
		
		String broadcastMsg = Constants.BroadcastPrefix + ",";
		DatagramSocket boardCastSocket;
		
		try {
			InetAddress clientAddress = InetAddress.getLocalHost();
			broadcastMsg = broadcastMsg + clientAddress.getHostAddress();
			byte data[] = broadcastMsg.getBytes();
			
			boardCastSocket = new DatagramSocket();
			boardCastSocket.setBroadcast(true);
			//InetAddress[] onlineNode = getAllOnline();
			int i = 0;
			while (i++ < Constants.BroadcastTimes){
				DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), Constants.BroadcastPort);
				//System.out.println(addr);
				boardCastSocket.send(packet); 
				sleep(300);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
