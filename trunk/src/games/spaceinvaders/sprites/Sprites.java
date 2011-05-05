package games.spaceinvaders.sprites;

import games.spaceinvaders.Commons;
import java.awt.Image;
import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;

public class Sprites extends Sprite implements Commons
{
    private boolean visible = true;
    private Image image;
    protected boolean dying;

    public Sprites (BufferedImage image, int x, int y)
    {
        super(image, x, y);
    }
    
    public void die() 
    {
        visible = false;
    }

    public boolean isVisible() 
    {
        return visible;
    }

    protected void setVisible(boolean visible) 
    {
        this.visible = visible;
    }

    public void setDying(boolean dying) 
    {
        this.dying = dying;
    }

    public boolean isDying() 
    {
        return this.dying;
    }
}