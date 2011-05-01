package games.breakout;

import games.breakout.sprites.Paddle;
import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritegroups.SpriteGroup;

public class Breakout extends VoogaGame
{
    {distribute = true;}
    
    public static EventManager eventManager;
    
    public static void main (String[] args)
    {
        launchGame(new Breakout(), new Dimension(640, 480), false);
    }

    @Override
    public void updatePlayField (long elapsedTime) {}

    @Override
    public void initResources ()
    {
        eventManager = getEventManager();
        
        Paddle paddle = new Paddle(this, getWidth()/2, getHeight());
        getLevelManager().addPlayer(new SpriteGroup<Paddle>("paddle", paddle));
        getLevelManager().loadLevel(0);
    }
}
