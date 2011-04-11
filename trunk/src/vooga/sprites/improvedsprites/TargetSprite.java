package vooga.sprites.improvedsprites;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.interfaces.ICanTarget;
import vooga.sprites.improvedsprites.interfaces.ITargetable;
import vooga.sprites.spritebuilder.components.basic.TargetC;
import vooga.sprites.spritebuilder.components.basic.TargetIndicatorC;

public class TargetSprite extends AdvanceSprite
    implements ITargetable, ICanTarget
{

    private boolean amTargetted;

    public TargetSprite (BufferedImage[] image, double x, double y, BufferedImage indicator)
    {
        super(image, x, y);
        this.setIndicator(indicator);
    }

    public TargetSprite (BufferedImage[] image, BufferedImage indicator)
    {
        super(image);
        this.setIndicator(indicator);

    }

    @Override
    public void setTarget (ITargetable target)
    {
        if (!this.carriesComponent(TargetC.class))
            this.addComponent(new TargetC(target));
        else
            this.getComponent(TargetC.class).setTarget(target);
    }

    @Override
    public ITargetable getTarget ()
    {
        return this.getComponent(TargetC.class).getTarget();
    }

    @Override
    public boolean hasTarget ()
    {
        return this.getComponent(TargetC.class).isNull();
    }

    @Override
    public void target ()
    {
        amTargetted = true;
        
    }

    @Override
    public void detarget ()
    {
        amTargetted = false;
        
    }

    @Override
    public boolean isTargetted ()
    {
        return amTargetted;
    }


    @Override
    public Point2D getLocation ()
    {
        return new Point2D.Double(this.getX(), this.getY());
    }

    @Override
    public Dimension getSize ()
    {
        return new Dimension(this.width, this.height);
    }

    @Override
    public void setIndicator (BufferedImage b)
    {
        if (!this.carriesComponent(TargetIndicatorC.class))
            this.addComponent(new TargetIndicatorC(b,this));
        else
            this.getComponent(TargetIndicatorC.class).getIndicator().setImage(b);
    }

   
}
