package vooga.view.function;

import java.awt.geom.AffineTransform;

/**
 * Extends AbstractGraphicFunction. Implements the pan function by translating coordinates based on specified amounts
 * @author Alex Daniel
 *
 */
public class PanFunction extends AbstractGraphicsFunction
{
    //x and y pan amounts
    private double xpan;
    private double ypan;
    
    /**
     * Creates instance of the PanFunction
     * @param x pan amount
     * @param y pan amount
     */
    public PanFunction(double x, double y)
    {
        xpan = x;
        ypan = y;
    }

    /**
     * Performs pan function based on specified values
     */
    @Override
    public AffineTransform doFunction()
    {
        return AffineTransform.getTranslateInstance(xpan, ypan);
    }
    
    /**
     * Sets the x pan amount
     */
    public void setPanX(double x)
    {
        xpan = x;
    }
    
    /**
     * Sets the y pan amount
     */
    public void setPanY(double y)
    {
        ypan = y;
    }
    
    /**
     * Returns the x pan amount
     */
    public double getPanX()
    {
        return xpan;
    }
    
    /**
     * Returns the y pan amount
     */
    public double getPanY()
    {
        return ypan;
    }

}
