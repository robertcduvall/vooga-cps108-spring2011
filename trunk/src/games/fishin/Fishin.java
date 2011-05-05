package games.fishin;


import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.resources.images.ImageLoader;

/**
 * Catch fish. Save the day.
 * 
 * @author Anne Weng
 *
 */
public class Fishin extends VoogaGame {

    public static ImageLoader imageLoader;


    public static void main (String[] args)
    {
        launchGame(new Fishin(), new Dimension(640, 480), false);
    }

    @Override
    public void updatePlayField(long elapsedTime) {
    }

    @Override
    public void initResources() {
        imageLoader = getImageLoader();

        getLevelManager().loadLevel(0);
    }
    



}
