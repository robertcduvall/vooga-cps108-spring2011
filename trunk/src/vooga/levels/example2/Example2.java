package vooga.levels.example2;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import vooga.levels.LevelManager;
import vooga.sprites.improvedsprites.Sprite;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class Example2 extends Game {

	private LevelManager myLevelManager;
	private ArrayList<Sprite> myPlayers;
	
    public static void main (String[] args)
    {
        GameLoader game = new GameLoader();
        game.setup(new Example2(), new Dimension(640,480), false);
        game.start();
    }
	
	@Override
	public void initResources() {
		myPlayers = new ArrayList<Sprite>(); 
		myPlayers.add(new Sprite());
		myLevelManager = new LevelManager(this, myPlayers);
		myLevelManager.loadLevel(0);
	}

	@Override
	public void update(long elapsedTime) {
		myLevelManager.update(elapsedTime);

	}

	@Override
	public void render(Graphics2D g) {
		myLevelManager.render(g);

	}

}
