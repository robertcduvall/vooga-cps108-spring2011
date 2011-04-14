package vooga.view.graphics;

import graphics.GraphicsGame;

import java.awt.geom.AffineTransform;
import java.util.List;

import com.golden.gamedev.Game;

import function.AbstractGraphicsFunction;

/**
 * Maintains AbstractGraphicsFunctions
 * Calls functions on AffineTransform
 * @author Alex Daniel
 *
 */
public class AdvancedGraphics {
    
    private boolean myState;
    
    //Graphics States
    private List<AbstractGraphicsFunction> myFunctions;
    
    /**
     * Creates instance of AdvancedGraphics
     * @param game object
     */
    public AdvancedGraphics(GraphicsGame game)
    {
        myFunctions = game.graphicsFunctions();
        myState = false;
    }
    
    /**
     * Sets the advancedGraphics feature active/inactive
     * @param bool
     */
    public void setActivityState(boolean bool)
    {
        myState = bool;
    }
    
    /**
     * The activity state of advancedGraphics
     * @return boolean
     */
    public boolean getActivityState()
    {
        return myState;
    }
    
    /**
     * Returns the AffineTransform that has been modified by active functions.
     * If no functions exist, return null.
     * @return AffineTransform
     */
    public AffineTransform getFunctionState()
    {
        if (myFunctions != null && myState)
        {
            AffineTransform result = new AffineTransform();
            for (AbstractGraphicsFunction function : myFunctions)
            {
                result.concatenate(function.doFunction());
            }
            return result;
        }
        else
        {
            return null;
        }
    }

}