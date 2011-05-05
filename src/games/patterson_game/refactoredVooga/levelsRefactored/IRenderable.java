package games.patterson_game.refactoredVooga.levelsRefactored;

import java.awt.Graphics2D;


/**
 * Any object that can be render. If added to a <code>VoogaPlayField</code> this
 * interface is useful for ensuring that the object is rendered every time
 * <code>VoogaPlayField</code> is rendered.
 * 
 * @author Andrew Patterson
 */
public interface IRenderable
{
    /**
     * Renders the object which implements this interface
     * 
     * @param g Graphics2D used to render this object
     */
    public void render (Graphics2D g);
}
