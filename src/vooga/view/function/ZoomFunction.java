package vooga.view.function;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Extension of AbstractGraphicsFunction. Implementation of the Zoom function
 * @author Alex
 *
 */
public class ZoomFunction extends AbstractGraphicsFunction {
    
    //Initially Set to 1 if scale not set.
    private int scalex = 1;
    private int scaley = 1;

    /**
     * Overrides abstract method in super class. Performs zoom function based on scaling.
     */
    @Override
    public AffineTransform doFunction() {
        return AffineTransform.getScaleInstance(scalex, scaley);
    }
    
    /**
     * Sets the x scaling component
     * @param x
     */
    public void setScaleX(int x)
    {
        scalex = x;
    }
    
    /**
     * Sets the y scaling component
     * @param y
     */
    public void setScaleY(int y)
    {
        scaley = y;
    }
    

}
