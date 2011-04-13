package function;

import java.awt.Graphics2D;

/**
 * Abstract class representing graphic transformation function
 * @author Alex
 *
 */
public abstract class AbstractGraphicsFunction {
    
    protected boolean myStatus = false;
    
    /**
     * Perform object's graphic manipulation function
     * @param graphics object
     * @return modified graphics object
     */
    public abstract Graphics2D doFunction(Graphics2D g);
    
    /**
     * Returns whether function is active or dormant.
     * @return boolean of function status
     */
    public boolean getFunctionStatus()
    {
        return myStatus;
    }
    
    /**
     * Sets the functions status
     * @param bool
     */
    public void setFunctionStatus(boolean bool)
    {
        myStatus = bool;
    }
}
