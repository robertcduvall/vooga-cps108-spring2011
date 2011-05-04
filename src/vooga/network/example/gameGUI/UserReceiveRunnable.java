package vooga.network.example.gameGUI;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;


import vooga.network.INetworkEngine;

public class UserReceiveRunnable implements Runnable
{
	private GameGUI gui;
	private INetworkEngine engine;
	public UserReceiveRunnable(GameGUI gui, INetworkEngine e){
		this.gui = gui;
		this.engine = e;
	}

	// In this step the user receivable should deal with two kind of control commands, create host and join
	@Override
	public void run()
	{
		List<Object> received;
		while(engine.isConnected()){
			received = engine.update();
			for(Object object : received){
				if(object instanceof StartGame){
					gui.startGame(((StartGame)object).port);
				}else{
					try{
						gui.messageShow.append((String)object+"\n");
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		gui.messageShow.append("the user receive runnable ends\n");
	}
}
