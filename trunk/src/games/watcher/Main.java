package games.watcher;

import games.watcher.sprites.Leader;
import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * Walker, the goal of this game is to successfully lead your walkers to the other side of 
 * the screen without them getting lost or dying. It's a fairly simple concepts but it's bundles of fun. 
 * 
 * @author Conrad
 *
 */
public class Main extends VoogaGame
{
   // {distribute = true;}
    
    /*
     * We need these global variables because the level loader doesn't allow
     * sprites to access the game class. 
     */
    public static EventManager eventManager;
    public static ImageLoader imageLoader;
    
    public static void main (String[] args)
    {
        launchGame(new Main(), new Dimension(640, 480), false);
    }

    @Override
    public void updatePlayField (long elapsedTime) {}

    @Override
    public void initResources ()
    {
        eventManager = getEventManager();
        imageLoader = getImageLoader();
        
        Leader detective = new Leader(this, getWidth()/2, getHeight());
        getLevelManager().addPlayer(new SpriteGroup<Leader>("leader", detective)); //should be detective 
        getLevelManager().loadLevel(0);
    }
}
