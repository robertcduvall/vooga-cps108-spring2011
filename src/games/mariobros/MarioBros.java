package games.mariobros;

import games.mariobros.sprites.Lander;

import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * Breakout. Bounce a ball off a paddle to destroy bricks.
 * 
 * @author Misha
 *
 */
public class MarioBros extends VoogaGame
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
        launchGame(new MarioBros(), new Dimension(640, 480), false);
    }

    @Override
    public void updatePlayField (long elapsedTime) {}

    @Override
    public void initResources ()
    {
        eventManager = getEventManager();
        imageLoader = getImageLoader();
        
        Lander mario = new Lander(this, getWidth()/2, getHeight()/8);
        getLevelManager().addPlayer(new SpriteGroup<Lander>("lander", mario));
        getLevelManager().loadLevel(0);
    }
}
