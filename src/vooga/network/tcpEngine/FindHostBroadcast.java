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
	
	//TODO: delete
	public static InetAddress[] getAllOnline() {
		
//		for (InetAddress addr : onlineNode) {
//		DatagramPacket packet = new DatagramPacket(data, data.length, addr, Constants.BroadcastPort);
//		System.out.println(addr);
//		boardCastSocket.send(packet); 
//		sleep(100);
//	}
	
		List<InetAddress> v = new ArrayList<InetAddress>(50);
		try {
			Process process = Runtime.getRuntime().exec("arp -a");
			InputStreamReader inputStr = new InputStreamReader(process
					.getInputStream());
			BufferedReader br = new BufferedReader(inputStr);
			String temp = "";
			br.readLine();
			br.readLine();
			br.readLine();
			while ((temp = br.readLine()) != null) {
				StringTokenizer tokens = new StringTokenizer(temp);
				String x;
				InetAddress add = InetAddress.getByName(x = tokens.nextToken());
				// System.out.println(x);
				v.add(add);
				// System.out.println(add);
			}
			v.add(InetAddress.getLocalHost());
			process.destroy();
			br.close();
			inputStr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int cap = v.size();
		InetAddress[] addrs = new InetAddress[cap];
		for (int i = 0; i < cap; i++) {
			addrs[i] = (InetAddress) v.get(i);
		}
		return addrs;

	}
}
