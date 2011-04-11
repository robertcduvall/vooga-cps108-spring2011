package vooga.sprites.util.targetindicators;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.BaseSprite;
import vooga.sprites.improvedsprites.interfaces.ICanTarget;
import vooga.sprites.improvedsprites.interfaces.IRender;
import vooga.sprites.improvedsprites.interfaces.ITargetable;

public class TargetIndicator extends BaseSprite implements IRender
{

    ITargetable myFocus;


    @Override
    public void render (Graphics2D g)
    {
        if (myFocus.isTargetted())
            this.render(g, (int)(myFocus.getLocation().getX()*1.2), (int)(myFocus.getLocation().getY()*1.2));
    }


    @Override
    public void render (Graphics2D g, int x, int y)
    {
        if (myFocus.isTargetted())
            super.render(g, x, y);
    }


 
    public TargetIndicator (BufferedImage image, ITargetable f)
    {
        super(image);
        myFocus = f;
        this.width = (int) f.getSize().getWidth();
        this.height = (int) f.getSize().getHeight();
        
    }






   

}
