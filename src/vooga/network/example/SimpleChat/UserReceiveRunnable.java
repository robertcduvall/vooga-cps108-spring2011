package vooga.network.example.SimpleChat;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import vooga.network.INetworkEngine;


public class UserReceiveRunnable implements Runnable
{
	INetworkEngine e;
	public UserReceiveRunnable(INetworkEngine e){
		this.e = e;
	}
	
	@Override
	public void run()
	{
		List<Object> received;
		while(e.isConnected()){
			received = e.update();
			for(Object obj : received){
				System.out.println((String)obj);
			}
		}
		System.out.println("the user receive runnable ends");

	}

}
