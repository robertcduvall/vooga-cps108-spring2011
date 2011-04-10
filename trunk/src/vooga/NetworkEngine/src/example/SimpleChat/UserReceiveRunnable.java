package example.SimpleChat;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import network.NetworkEngine;

public class UserReceiveRunnable implements Runnable
{
	NetworkEngine e;
	public UserReceiveRunnable(NetworkEngine e){
		this.e = e;
	}
	
	@Override
	public void run()
	{
		List<Object> received;
		while(!e.isClosed()){
			received = e.update();
			for(Object obj : received){
				System.out.println((String)obj);
			}
		}
		System.out.println("the user receive runnable ends");

	}

}
