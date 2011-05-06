package games.leapingSquirrel;

import games.leapingSquirrel.sprites.Squirrel;
import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.sprites.spritegroups.SpriteGroup;


/**
 * @author Andrea Scripa
 */

public class LeapingSquirrelGame extends VoogaGame
{
    public static EventManager myEventManager;
    public static final int SQUIRREL_START_X = 350;


    public static void main (String[] args)
    {
        launchGame(new LeapingSquirrelGame(), new Dimension(700, 700), false);
    }


    @Override
    public void updatePlayField (long elapsedTime)
    {}


    @Override
    public void initResources ()
    {
        myEventManager = getEventManager();
        Squirrel ds =
            new Squirrel(getResourceManager().getImageLoader().getImage("Squirrel_Right"),
                         SQUIRREL_START_X, 650, this);
        getLevelManager().addPlayer(new SpriteGroup<Squirrel>("squirrel", ds));
        getLevelManager().loadLevel(0);

    }
}
