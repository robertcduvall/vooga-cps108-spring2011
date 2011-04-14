package vooga.view.function;

import java.awt.geom.AffineTransform;

/**
 * Abstract class representing graphic transformation function
 * @author Alex
 *
 */
public abstract class AbstractGraphicsFunction {
    
   
    /**
     * Perform object's graphic manipulation function
     * @param graphics object
     * @return modified graphics object
     */
    public abstract AffineTransform doFunction();
    
}
