package games.breakout;

import games.breakout.sprites.Paddle;
import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritegroups.SpriteGroup;

public class Breakout extends VoogaGame
{
    {distribute = true;}
    
    /*
     * We need these global variables because the level loader doesn't allow
     * sprites to access the game class. 
     */
    public static EventManager eventManager;
    public static ImageLoader imageLoader;
    
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
        imageLoader = getImageLoader();
        
        Paddle paddle = new Paddle(this, getWidth()/2, getHeight());
        getLevelManager().addPlayer(new SpriteGroup<Paddle>("paddle", paddle));
        getLevelManager().loadLevel(0);
    }
}
