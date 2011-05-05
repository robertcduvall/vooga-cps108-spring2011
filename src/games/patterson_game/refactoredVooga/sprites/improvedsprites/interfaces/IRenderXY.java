package games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces;

import games.patterson_game.refactoredVooga.levelsRefactored.IRenderable;
import java.awt.Graphics2D;


/**
 * Renders an object renderable. Uses java.awt.Graphics2D
 * 
 * @author Julian
 *
 */
public interface IRenderXY extends IRenderable
{





    /**
     * Renders sprite image to specified graphics context and specified
     * location.
     * 
     * @param g graphics context
     * @param x screen x-coordinate
     * @param y screen y-coordinate
     */
    public abstract void render (Graphics2D g, int x, int y);



    

}
