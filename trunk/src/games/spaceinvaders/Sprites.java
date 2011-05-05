package games.spaceinvaders;

import java.awt.Image;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class Sprites extends Sprite implements Commons
{
    private boolean visible;
    private Image image;
    protected int x;
    protected int y;
    protected boolean dying;
    protected int dx;

    public Sprites(BufferedImage image) 
    {
        super(image);
        visible = true;
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

//    @Override
//    public void setImage(BufferedImage image) 
//    {
//        set
//        this.image = image;
//    }
//
//    @Override
//    public BufferedImage getImage() 
//    {
//        return (BufferedImage)image;
//    }

    public void setX(int x) 
    {
        this.x = x;
    }

    public void setY(int y) 
    {
        this.y = y;
    }

    //getX and getY

    public void setDying(boolean dying) 
    {
        this.dying = dying;
    }

    public boolean isDying() 
    {
        return this.dying;
    }
}