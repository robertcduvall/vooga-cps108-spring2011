package vooga.sprites.improvedsprites.interfaces;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public interface ITargetable
{

    void target();
    
    void detarget();
    
    boolean isTargetted();
    
    Point2D getLocation();


    Dimension getSize ();
    
}