package vooga.view.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.Game;

import vooga.view.function.AbstractGraphicsFunction;
import vooga.view.function.ZoomFunction;

/**
 * Wrapper Class for Graphics2D.
 * Keeps track of active states and calls functions.
 * @author Alex Daniel
 *
 */
public class AdvancedGraphics {

    //AdvancedGraphics resources
    private Graphics2D myGraphics;
    private Game myGame;
    
    //Graphics States
    private List<AbstractGraphicsFunction> myFunctions;
    
    /**
     * Creates instance of AdvancedGraphics
     * @param game object
     */
    public AdvancedGraphics(Game game)
    {
        myGame = game;
        myGraphics = myGame.bsGraphics.getBackBuffer();
        myFunctions = new ArrayList<AbstractGraphicsFunction>();
        this.createFunctionObjects();
        
        
        System.out.println("Initial: " + myGraphics.toString());
        
    }
    
    //TODO: Resource/reflection
    private void createFunctionObjects()
    {
        myFunctions.add(new ZoomFunction());
    }
    
    /**
     * Returns the Graphics2D that has been modified by active functions
     * @return modified graphics
     */
    public Graphics2D getState()
    {
        for(AbstractGraphicsFunction function: myFunctions)
        {
            if(function.getFunctionStatus())
            {
                myGraphics = function.doFunction(myGraphics);
            }
        }
        return myGraphics;
    }

}