package vooga.levels;

import java.awt.Graphics2D;


/**
 * Any object that can be rendered. If added to a VoogaPlayField, this interface
 * is useful for ensuring that the object is rendered every time VoogaPlayField
 * is rendered.
 * 
 * @author Andrew Patterson
 */
public interface IRenderable
{
    /**
     * Renders the object which implements this interface
     * 
     * @param graphics
     */
    public void render (Graphics2D g);
}
