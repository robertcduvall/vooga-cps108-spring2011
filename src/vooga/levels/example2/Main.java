package vooga.levels.example2;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.levels.LevelManager;
import vooga.levels.example.main.CustomGame;
import vooga.player.Player;
import vooga.sprites.improvedsprites.Sprite;

import com.golden.gamedev.GameLoader;

public class Main extends VoogaGame {

	private LevelManager myLevelManager;
	private Collection<Player> myPlayers;
	
    public static void main (String[] args)
    {
        GameLoader game = new GameLoader();
        game.setup(new Main(), new Dimension(640,480), false);
        game.start();
    }
	
	@Override
	public void initResources() {
		myPlayers = new ArrayList<Player>(); 
		myLevelManager = new LevelManager(this, myPlayers);
		
		// TODO Auto-generated method stub

	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePlayField(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
