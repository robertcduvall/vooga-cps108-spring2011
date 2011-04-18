package vooga.leveleditor.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Renders and draws the level. Allows the user to interact with sprites that
 * are on it.
 */
public class Viewport extends JLayeredPane
{
    
    private DrawingBoard owner;
    
    public Viewport(DrawingBoard owner, int width, int height)
    {
        super();
        this.owner = owner;
        setPreferredSize(new Dimension(width, height));
        addMouseMotionListener(new MouseTracker());
    }
    
    /**
     * Follows the mouse and updates the status bar with the mouse coordinates.
     */
    private class MouseTracker implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            owner.setStatusBar(String.format("(%d, %d)", x, y));
        }
        
        public void mouseDragged(MouseEvent e)
        {
            return;
        }
    }

}
