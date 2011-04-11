package vooga.sprites.improvedsprites.interfaces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface ICanTarget
{

    void setTarget(ITargetable target);
    
    ITargetable getTarget();
    
    void setIndicator(BufferedImage b);
    
    boolean hasTarget();
    
}
