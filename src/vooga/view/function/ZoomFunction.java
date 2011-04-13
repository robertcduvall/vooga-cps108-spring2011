package vooga.view.function;

import java.awt.Graphics2D;

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
    public Graphics2D doFunction(Graphics2D g) {
        System.out.println("ZoomFunction: " + g.toString());
        g.scale(scalex, scaley);
        System.out.println("ZoomFunctionPerformed: " + g.toString());
        return g;
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
