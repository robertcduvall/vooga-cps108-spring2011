package games.starshipdefender.gameobjects;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;

public class EnemyShip extends Sprite
{
    private VoogaGame myGame;
    
    
    public EnemyShip(VoogaGame game)
    {
        myGame = game;
    }
    
    public EnemyShip(VoogaGame game, double x, double y)
    {
        super(x,y);
        myGame = game;
    }
    
    public EnemyShip(VoogaGame game, BufferedImage image, double x, double y)
    {
        super(image, x, y);
        myGame = game;
    }
    
    private void randomMovement()
    {
        //TODO: Create random enemy movement
    }
}
