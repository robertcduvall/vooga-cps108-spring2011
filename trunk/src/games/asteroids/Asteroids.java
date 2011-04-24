package games.asteroids;

import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;


public class Asteroids extends VoogaGame
{
    public static void main (String[] args)
    {
        launchGame(new Asteroids(), new Dimension(640, 480), false);
    }

    private Ship myShip;


    @Override
    public void initResources ()
    {
        myShip = new Ship(this);
        myShip.setX(150);
        myShip.setY(150);
        getLevelManager().addPlayer(new SpriteGroup<Sprite>("Ship", myShip));
        
        // TODO getResourceManager().getKeyMap().registerEventHandler(this);

        getLevelManager().loadLevel(0);
    }


    @Override
    public void updatePlayField (long elapsedTime)
    {
        return;
    }
}
