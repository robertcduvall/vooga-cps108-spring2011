package vooga.sprites.util.targetindicators;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.BaseSprite;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.IRender;
import vooga.sprites.improvedsprites.interfaces.ITargetable;
import vooga.sprites.spritebuilder.components.basic.TargetableC;

public class TargetIndicator extends BaseSprite implements IRender
{

    Sprite myFocus;


    @Override
    public void render (Graphics2D g)
    {
        if (myFocus.getComponent(TargetableC.class).isTargetted())
            this.render(g, (int)(myFocus.getWidth()*1.2), (int)(myFocus.getHeight()*1.2));
    }


    @Override
    public void render (Graphics2D g, int x, int y)
    {
        if (myFocus.getComponent(TargetableC.class).isTargetted())
            super.render(g, x, y);
    }


 
    public TargetIndicator (BufferedImage image, Sprite f)
    {
        super(image);
        myFocus = f;
        this.width = (int) f.getWidth();
        this.height = (int) f.getHeight();
        
    }






   

}
