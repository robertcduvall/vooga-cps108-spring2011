package example.SimpleChat;
import network.NetworkEngine;
import tcpEngine.TCPNetworkEngine;


public class AsClient
{
	public static void main(String[] argv){
		NetworkEngine obj = new TCPNetworkEngine(8999);
		obj.connect("127.0.0.1");
		(new Thread(new UserReceiveRunnable(obj))).start();
		(new Thread(new UserSendRunnable(obj))).start();
	}
}
