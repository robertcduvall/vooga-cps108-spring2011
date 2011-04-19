package vooga.leveleditor.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

/**
 * Renders and draws the level. Allows the user to interact with sprites that
 * are on it.
 * @author Alex Lee
 * @author Charlie Hatcher
 */
public class Viewport extends JLayeredPane
{
    private int x,y;
    private DrawingBoard owner;
    private ArrayList<DraggableImage> myImages;
    
    public Viewport(DrawingBoard owner, int width, int height)
    {
        super();
        myImages = new ArrayList<DraggableImage>();
        this.owner = owner;
        setPreferredSize(new Dimension(width, height));
        addMouseMotionListener(new MouseTracker());
        addMouseListener(new MouseClick());
    }
    
    void addImage(DraggableImage i){
    	myImages.add(i);
    }
    
    /**
     * Follows the mouse and updates the status bar with the mouse coordinates.
     */
    private class MouseTracker implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent e)
        {
            x = e.getX();
            y = e.getY();
            owner.setStatusBar(String.format("(%d, %d)", x, y));
        }
        
        public void mouseDragged(MouseEvent e)
        {
            return;
        }
    }
    private class MouseClick implements MouseListener{

    	/**
    	 * If the mouse is hovering over the image on the viewport and is clicked,
    	 * set up that image to become draggable.
    	 */
		@Override
		public void mouseClicked(MouseEvent arg0) {
			/*
			 * TODO See if it is possible to get rid of if statements
			 */
			if(myImages.size()>0){
				for(DraggableImage i: myImages){
					if(((i.getX()-i.getWidth())<x && (i.getX()+i.getWidth())>x)
							&& ((i.getY()-i.getHeight())<y) &&(i.getY()+i.getHeight())>y){
						if(i.getFlag()){
							i.setFlag(false);
						}
						else i.setFlag(true);
					}
				}
			}
			return;
		}

		/**
		 * the rest of these methods are unneeded, but come standard with the
		 * MouseListener interface
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {
			return;
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			return;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
			return;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			return;
		}

    }

}
