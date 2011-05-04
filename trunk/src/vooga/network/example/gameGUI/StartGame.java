package vooga.network.example.gameGUI;

import java.io.Serializable;

public class StartGame implements Serializable{
	public int port = 0;
	
	public StartGame(int port){
		this.port = port;
	}
}
