package games.frogger;
/**
 * @author Wesley Brown
 */

import games.frogger.sprites.Frog;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.sprites.spritegroups.SpriteGroup;

public class Frogger extends VoogaGame {

	public static EventManager myEventManager;
	public static final int FROG_Y_START = 550;
	
	public static void main (String[] args)
    {
        launchGame(new Frogger(), new Dimension(600, 646), false);
    }
	
	@Override
	public void updatePlayField(long elapsedTime) {
	}

	@Override
	public void initResources() {
		myEventManager = getEventManager();
		
		Frog f = new Frog(getResourceManager().getImageLoader().getImage("frog"), getWidth()/2, FROG_Y_START, this);
		getLevelManager().addPlayer(new SpriteGroup<Frog>("frog", f));
		getLevelManager().loadLevel(0);

	}

}
