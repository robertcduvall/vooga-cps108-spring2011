package vooga.view.graphics;

import vooga.view.function.AbstractGraphicsFunction;
import vooga.view.graphics.AdvancedGraphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;

import com.golden.gamedev.Game;

/**
 * Abstract class used to mediate communication between game and graphics game.
 * Holds instance of advancedGraphics to be used for transformations.
 * 
 * @author Alex Daniel
 * 
 */
public abstract class GraphicsGame extends Game {

    protected AdvancedGraphics myAdvancedGraphics;
    
    /**
     * Creates advancedGraphics object to manage graphics state
     */
    @Override
    public void initResources()
    {
        myAdvancedGraphics = new AdvancedGraphics(this);
        this.initializeResources();
    }

    /**
     * Must be implemented to initialize game resources in game that extends GraphicsGame.
     */
    public abstract void initializeResources();
    
    /**
     * Must be implemented to update game objects in extension of GraphicsGame.
     */
    @Override
    public abstract void update(long elapsedTime);

    /**
     * Renders graphics based on state of advancedGraphics
     */
    @Override
    public void render(Graphics2D g)
    {
        if (myAdvancedGraphics.getFunctionState() != null)
        {
            AffineTransform old = g.getTransform();
            g.setTransform(myAdvancedGraphics.getFunctionState());
            transformedRender(g);
            g.setTransform(old);
        }
        this.transformedRender(g);
    }
    
    /**
     * Implemented by the user to render game objects
     * @param advancedgraphics
     */
    public abstract void transformedRender (Graphics2D graphics);
    
    /**
     * User specifies what type of functions required.
     * @return List of functions maintained in advancedGraphics
     */
    public abstract List<AbstractGraphicsFunction> graphicsFunctions();
    
    /**
     * Convenience method for setting advancedGraphics state
     * @param bool
     */
    public void setGraphicsActivityState(boolean bool)
    {
        myAdvancedGraphics.setActivityState(bool);
    }
    
    /**
     * Convenience method for getting advancedGraphics activity state
     * @return boolean
     */
    public boolean getGraphicsActivityState()
    {
        return myAdvancedGraphics.getActivityState();
    }
}
