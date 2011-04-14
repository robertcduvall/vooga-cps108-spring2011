package vooga.view.function;

import java.awt.geom.AffineTransform;

/**
 * Contains the behavior and information for the rotation function that extends AbstractGraphicsFunction
 * @author Alex Daniel
 *
 */
public class RotateFunction extends AbstractGraphicsFunction
{
    //Rotation information in radians
    private double myRotation;
    
    /**
     * Creates instance of RotationFunction with rotation amount in radians
     * @param rotation amount in radians
     */
    public RotateFunction(double rotation)
    {
        myRotation = rotation;
    }

    /**
     * Returns a rotation instance of AffineTransform based on the specified parameter
     */
    @Override
    public AffineTransform doFunction()
    {
        return AffineTransform.getRotateInstance(myRotation);
    }
    
    /**
     * Sets the rotation amount
     */
    public void setRotation(double rotation)
    {
        myRotation = rotation;
    }
    
    /**
     * Returns the functions rotation amount in radians
     */
    public double getRotation()
    {
        return myRotation;
    }

}
