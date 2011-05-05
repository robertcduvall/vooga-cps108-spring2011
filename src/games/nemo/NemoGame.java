package games.nemo;

import games.breakout.Breakout;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;

public class NemoGame extends VoogaGame {

	public static EventManager eventManager;
    public static ImageLoader imageLoader;
	
	@Override
	public void updatePlayField(long elapsedTime) {}

	@Override
	public void initResources() {
		eventManager = getEventManager();
        imageLoader = getImageLoader();
        
        getLevelManager().loadLevel(0);
	}
	
	public static void main (String[] args)
    {
        launchGame(new NemoGame(), new Dimension(800, 600), false);
    }

}
