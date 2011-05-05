package games.leapingSquirrel;

import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;

/**
 * 
 * @author Andrea Scripa
 *
 */

public class LeapingSquirrelGame extends VoogaGame
{
    public static EventManager myEventManager;
            
    public static void main (String[] args)
    {
        launchGame(new LeapingSquirrelGame(), new Dimension(800, 600), false);
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
