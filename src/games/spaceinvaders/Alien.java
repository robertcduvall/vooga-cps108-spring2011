package games.spaceinvaders;

import java.awt.image.BufferedImage;

public class Alien extends Sprites
{
    private Bomb bomb;

    public Alien(BufferedImage alienImg, BufferedImage bombImg, int x, int y) 
    {
        super(alienImg);
        this.x = x;
        this.y = y;
        bomb = new Bomb(bombImg, x, y);
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
            super(image);
            setDestroyed(true);
            this.x = x;
            this.y = y;
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