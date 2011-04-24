package vooga.core;

import java.awt.Graphics2D;
import vooga.core.event.EventManager;


/**
 * @author Michael Ansel
 * @author Wesley Brown
 * @author David Cole
 * @author Shun Fan
 */
public interface VoogaState
{
    void update (long elapsedTime);


    void render (Graphics2D g);


    EventManager getEventManager ();
}
