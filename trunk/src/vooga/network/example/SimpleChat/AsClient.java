package vooga.network.example.SimpleChat;
import java.util.List;

import vooga.network.INetworkEngine;
import vooga.network.tcpEngine.ConnectInfo;
import vooga.network.tcpEngine.LocalNetworkEngine;


public class AsClient
{
	public static void main(String[] argv){
		INetworkEngine obj = new LocalNetworkEngine();
		List<ConnectInfo> result = obj.searchHost();
		if (!result.isEmpty()){
			for (ConnectInfo c : result){
				System.out.println("Host adress: " + c.getIPaddress());
			}
		}
		(new Thread(new UserReceiveRunnable(obj))).start();
		(new Thread(new UserSendRunnable(obj))).start();
	}
}
