package vooga.core;

import java.awt.Graphics2D;
import vooga.core.event.EventManager;


/**
 * VoogaState represents distinct portions of the game. Instead of having a
 * centralized update and render method within a main VoogaGame, VoogaStates
 * allows developers to separate 'update' and 'render' into smaller classes with 
 * focused responsibilities. For example there could be a VoogaState that 
 * chooses the level, or there could be a VoogaState that chooses the
 * menu.
 * @author Michael Ansel
 * @author Wesley Brown
 * @author David Cole
 * @author Shun Fan
 */
public interface VoogaState
{	
	/**
	 * This is basically the update method from GoldenT Game
	 * @param elapsedTime
	 */
    void update (long elapsedTime);

    /**
     * this is basically the render method from GoldenT Game
     * @param g
     */
    void render (Graphics2D g);

    /**
     * each VoogaState has its own EventManager. This allows for 
     * compartmentalized event handling.
     * @return
     */
    EventManager getEventManager ();
}
