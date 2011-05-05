package games.spaceinvaders.sprites;

import java.awt.image.BufferedImage;

public class Shot extends Sprites
{
    public Shot(BufferedImage image, int x, int y) 
    {
        super(image, x, y);
        setX(x - getWidth()/2);
        setY(y - getHeight());
        setAbsoluteSpeed(BULLET_SPEED);
    }
}
