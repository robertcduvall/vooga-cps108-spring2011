package games.frogger;
/**
 * @author Wesley Brown
 */

import games.frogger.sprites.CarA;
import games.frogger.sprites.components.VelocityC;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;

public class Frogger extends VoogaGame {

	public static EventManager myEventManager;
	
	public static void main (String[] args)
    {
        launchGame(new Frogger(), new Dimension(800, 600), false);
    }
	
	@Override
	public void updatePlayField(long elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initResources() {
		myEventManager = getEventManager();
		getLevelManager().loadLevel(0);

	}

}
