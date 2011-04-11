package vooga.sprites.spritebuilder.components.basic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.AnimatedSprite;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.IRender;
import vooga.sprites.improvedsprites.interfaces.ITargetable;
import vooga.sprites.spritebuilder.components.ISpriteUpdater;
import vooga.sprites.util.targetindicators.TargetIndicator;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.buildable.components.IComponent;

public class TargetIndicatorC extends BasicComponent implements IComponent, IRender
{
    
    TargetIndicator myIndicator;
    
    TargetIndicatorC(TargetIndicator ti){
        myIndicator = ti;
    }
    
    public TargetIndicatorC (BufferedImage buf, ITargetable t)
    {
        myIndicator = new TargetIndicator(buf, t);
    }
    
    
    @Override
    public void render (Graphics2D g)
    {
        render(g, 0, 0);
    }

    @Override
    public void render (Graphics2D g, int x, int y)
    {
        myIndicator.render(g, x, y);
    }

    @Override
    protected int compareTo (BasicComponent o)
    {
        // TODO how to compare target indicators?
        return 0;
    }

    @Override
    protected Object[] getFields ()
    {
        return new Object[]{myIndicator};
    }

    @Override
    protected void setFields (Object ... fields)
    {
        myIndicator = (TargetIndicator) fields[0];
    }

    public TargetIndicator getIndicator ()
    {
        return myIndicator;
    }


}
