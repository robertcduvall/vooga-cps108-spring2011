package vooga.network.example.gameGUI;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import vooga.arcade.parser.gameObject.ArcadeObject;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

/**
 * Object containing an instance of game and information associated with it.
 * This is used to transport game and its data around
 * 
 * @author KevinWang
 * @author Ethan Goh
 * 
 * This class is adepted from the Arcade project.
 * 
 * @author Roman
 * 
 */

public class ArcadeNetworkGame {

	private Game game;
	private Dimension dimension;
	private String name;
	private boolean isHost = false;
	private String serverIP = null;
	private int port = 0;

	public ArcadeNetworkGame(String name) {
		this.dimension = new Dimension(640,480);
		this.name = name;
	}

	/**
	 * start the game contained in the game object
	 */
	public void start() {
		createGame();
		Runnable r = new Runnable() {

			@Override
			public void run() {
				GameLoader loader = new GameLoader();
				loader.setup(game, dimension, false);
				game.start();
			}
		};

		Thread t = new Thread(r);
		t.start();
	}

	/**
	 * Creates the game instance
	 */
	private void createGame() {
		Class cls = null;
		
		try {
			cls = Class.forName(name);
			game = (Game) (cls.newInstance());
			
			((NetworkGame)game).setIsHost(isHost);
			((NetworkGame)game).setPort(port);
			((NetworkGame)game).setServerIP(serverIP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPort(int p){
		port = p;
	}
	
	public void setHost(boolean b){
		isHost = b;
	}
	
	public int getPort(){
		return port;
	}
	
	public void setIP(String IP){
		serverIP = IP;
	}
}
