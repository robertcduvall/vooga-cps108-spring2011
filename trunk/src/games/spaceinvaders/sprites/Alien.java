package games.spaceinvaders.sprites;

import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;
import games.spaceinvaders.Commons;

public class Alien extends Sprite implements Commons
{
    private Bomb bomb;

    public Alien(BufferedImage image, int x, int y) 
    {
        super(image, x, y);
        setX(x);
        setY(y);
        setAbsoluteSpeed(ALIEN_SPEED);
    }

    public void act(int direction) 
    {
        this.x += direction;
    }

    public Bomb getBomb() 
    {
        return bomb;
    }

    public class Bomb extends Sprite 
    {
        private boolean destroyed;

        public Bomb(BufferedImage image, int x, int y) 
        {
            super(image, x, y);
            setX(x);
            setY(y);
            setAbsoluteSpeed(BOMB_SPEED);
            setDestroyed(true);
        }

        public void setDestroyed(boolean destroyed) 
        {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() 
        {
            return destroyed;
        }
    }
}