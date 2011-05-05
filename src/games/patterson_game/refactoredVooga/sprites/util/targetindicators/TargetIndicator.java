package games.patterson_game.refactoredVooga.sprites.util.targetindicators;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.BaseSprite;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.IRenderXY;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.ITargetable;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic.TargetableC;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TargetIndicator extends BaseSprite implements IRenderXY
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
