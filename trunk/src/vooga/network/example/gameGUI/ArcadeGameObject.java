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
 */
public class ArcadeGameObject {

	protected Game game;
	protected Dimension dimension;

	public ArcadeGameObject() {
		this.dimension = new Dimension(640,480);
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
			cls = Class.forName("vooga.network.example.Game.MainGame");
			game = (Game) (cls.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
