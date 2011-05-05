package games.missilecommand;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.resources.images.ImageLoader;

/**
 * A very simple Missile Command-type game. You must heroically save the city
 * from certain destruction by a rain of bombs!
 * 
 * N.B. the destruction is actually certain. You cannot win.
 * 
 * @author Alex Lee (hl69)
 */
public class MissileCommandAlex extends VoogaGame
{

    public static final Dimension DEFAULT_SIZE = new Dimension(600, 800);
    
    public static ImageLoader loader;

    @Override
    public void updatePlayField(long elapsedTime)
    {

    }

    @Override
    public void initResources()
    {
        loader = getImageLoader();
        getLevelManager().loadLevel(0);
    }

    public static void main(String[] args)
    {
        launchGame(new MissileCommandAlex(), DEFAULT_SIZE, false); 
    }

}
