package games.asteroids;

import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;


public class Bullet extends Sprite
{
    private static final long serialVersionUID = 1L;

    public Bullet(BufferedImage image, int x, int y)
    {
        super(image, x, y);
        setVerticalSpeed(-0.1); 
    }
    
    public void destroy ()
    {
        // TODO Auto-generated method stub

    }

}
