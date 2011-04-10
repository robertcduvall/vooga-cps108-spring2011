package example.SimpleChat;
import network.NetworkEngine;
import tcpEngine.TCPNetworkEngine;


public class AsServer
{
	public static void main(String[] argv){
		NetworkEngine obj = new TCPNetworkEngine(8999);
		obj.createLocalHost(false);
		(new Thread(new UserReceiveRunnable(obj))).start();
		(new Thread(new UserSendRunnable(obj))).start();
	}
}
