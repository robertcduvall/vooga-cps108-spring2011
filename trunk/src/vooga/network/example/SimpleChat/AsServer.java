package vooga.network.example.SimpleChat;
import vooga.network.INetworkEngine;
import vooga.network.tcpEngine.LocalNetworkEngine;


public class AsServer
{
	public static void main(String[] argv){
		INetworkEngine obj = new LocalNetworkEngine();
		obj.createHost(false);
		(new Thread(new UserReceiveRunnable(obj))).start();
		(new Thread(new UserSendRunnable(obj))).start();
	}
}
