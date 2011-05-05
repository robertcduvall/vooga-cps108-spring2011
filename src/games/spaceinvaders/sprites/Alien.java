package games.spaceinvaders.sprites;

import java.awt.image.BufferedImage;

public class Alien extends Sprites
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
    
    public class Bomb extends Sprites 
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