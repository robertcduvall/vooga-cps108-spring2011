package graphics;

import java.awt.Graphics2D;

import com.golden.gamedev.Game;

/**
 * Abstract class used to mediate communication between game and graphics game.
 * Holds instance of advancedGraphics to be used for transformations.
 * 
 * @author Alex
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
    public void render(Graphics2D g) {
        this.renderGraphics(myAdvancedGraphics.getState());
    }
    
    /**
     * Implemented by the user to render game objects
     * @param advancedgraphics
     */
    public abstract void renderGraphics(Graphics2D graphics);
    
}
