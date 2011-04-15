package vooga.view.function;

import java.awt.geom.AffineTransform;
import vooga.view.function.AbstractGraphicsFunction;

/**
 * Extension of AbstractGraphicsFunction. Implementation of the Zoom function
 * @author Alex
 *
 */
public class ZoomFunction extends AbstractGraphicsFunction {
    
    //Initially Set to 1 if scale not set.
    private int scalex;
    private int scaley;
    
    /**
     * Creates instance of zoom function
     * @param x scale amount
     * @param y scale amount
     */
    public ZoomFunction(int x, int y) {
        scalex = x;
        scaley = y;
    }

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
    
    /**
     * Returns the x scale amount
     */
    public int getScaleX()
    {
        return scalex;
    }
    
    /**
     * Returns the y scale amount
     */
    public int getScaleY()
    {
        return scaley;
    }

}
