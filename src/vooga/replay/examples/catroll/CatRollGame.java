package vooga.replay.examples.catroll;

import java.awt.Dimension;

import vooga.replay.Replay;
import vooga.replay.StateTableFileManager;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class CatRollGame extends GameEngine {

	private StateTableFileManager stfm = new StateTableFileManager();

	@Override
	public GameObject getGame(int id) {
		switch (id) {
		case 0:
			return new MainMenu(this);
		case 1:
			return new Level1(this);
		case 2:
			try {
				return stfm.returnStateTableFromFile("st").replayTable(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		default:
			return new MainMenu(this);
		}
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		CatRollGame cat = new CatRollGame();
		game.setup(cat, new Dimension(800, 600), false);
		game.start();
	}

}
