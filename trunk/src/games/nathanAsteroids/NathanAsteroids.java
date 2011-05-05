package games.nathanAsteroids;

import games.nathanAsteroids.ship.Ship;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import vooga.core.VoogaGame;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

public class NathanAsteroids extends VoogaGame {

    public static void main (String[] args)
    {
        launchGame(new NathanAsteroids(), new Dimension(800, 600), false);
    }

    private Ship myShip;


    @Override
    public void initResources ()
    {
//        myShip = new Ship(this);
//        myShip.setX(150);
//        myShip.setY(150);
//        myShip.addComponent(new CollisionCircleC(myShip.getCenterPoint(), myShip.getWidth()/4));
//        getLevelManager().addPlayer(new SpriteGroup<Ship>("ship", myShip));
        
        // TODO getResourceManager().getKeyMap().registerEventHandler(this);

        
        getLevelManager().loadLevel(0);
    }


    @Override
    public void updatePlayField (long elapsedTime)
    {
        return;
    }
}

