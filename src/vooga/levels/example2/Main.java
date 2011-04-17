package vooga.levels.example2;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

import vooga.levels.LevelManager;
import vooga.levels.example.main.CustomGame;
import vooga.player.Player;
import vooga.sprites.improvedsprites.Sprite;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class Main extends Game {

	private LevelManager myLevelManager;
	private Collection<Sprite> myPlayers;
	
    public static void main (String[] args)
    {
        GameLoader game = new GameLoader();
        game.setup(new Main(), new Dimension(640,480), false);
        game.start();
    }
	
	@Override
	public void initResources() {
		myPlayers = new ArrayList<Sprite>(); 
		myPlayers.add(new Sprite());
		myLevelManager = new LevelManager()
		
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

}
