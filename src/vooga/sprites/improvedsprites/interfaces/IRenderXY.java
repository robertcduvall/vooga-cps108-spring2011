package vooga.sprites.improvedsprites.interfaces;

import java.awt.Graphics2D;

import vooga.levels.IRenderable;

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
