package vooga.network.example.gameGUI;

import java.util.List;

import vooga.network.INetworkEngine;
import vooga.network.tcpEngine.ConnectInfo;

public class UpdateConnectionInfo implements Runnable
{
	private GameGUI gui;
	private INetworkEngine engine;
	public UpdateConnectionInfo(GameGUI gui, INetworkEngine e){
		this.gui = gui;
		this.engine = e;
	}

	@Override
	public void run()
	{
		while(engine.isConnected()){
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				break;
			}

			
		}

	}

}
