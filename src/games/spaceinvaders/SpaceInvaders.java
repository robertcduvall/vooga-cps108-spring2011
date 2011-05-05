package games.spaceinvaders;

import games.spaceinvaders.sprites.Player;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * Space Invader game
 * @author Chao Chen
 *
 */

public class SpaceInvaders extends VoogaGame
{
    {distribute = true;}
    
    public static EventManager eventManager;
    public static ImageLoader imageLoader;
    
    public static void main (String[] args)
    {
        launchGame(new SpaceInvaders(), new Dimension(640, 480), false);
    }

    @Override
    public void updatePlayField (long elapsedTime) 
    {
        
    }

    @Override
    public void initResources ()
    {
        eventManager = getEventManager();
        imageLoader = getImageLoader();
        
        Player player = new Player(this, getWidth()/2, getHeight());
        getLevelManager().addPlayer(new SpriteGroup<Player>("player", player));
        getLevelManager().loadLevel(0);
    }
}